package com.stone.lib.compilse;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import com.stone.lib.annotations.router.Route;
import com.stone.lib.annotations.router.entity.RouteMeta;
import com.stone.lib.compilse.util.Consts;
import com.stone.lib.compilse.util.Log;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.util.*;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-13 13:13
 */
@AutoService(Processor.class)
//支持处理的注解
@SupportedAnnotationTypes({Consts.ANN_TYPE_ROUTE})
@SupportedOptions(Consts.ARGUMENTS_NAME)//对应于 gradle 中 javaCompileOptions.annotationProcessorOptions内的配置
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class RouteProcessor extends AbstractProcessor {

    //    private Messager messager;//build 控制台 打印日志
    private Filer filer; //文件
    private Elements elements; //元素工具类； 类、函数、属性
    private Types types; //类信息工具类

    private Log log;

    /**
     * key:组名 value:类名
     */
    private Map<String, String> rootMap = new TreeMap<>();
    /**
     * 分组 key:组名 value:对应组的路由信息
     */
    private Map<String, List<RouteMeta>> groupMap = new HashMap<>();

    /**
     * 参数
     */
    private String moduleName;

    /*
     * 会自动被注解处理工具调用，并传入ProcessingEnviroment参数，通过该参数可以获取到很多有用的工具类: Elements , Types , Filer 等等
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        log = Log.newInstance(processingEnv.getMessager());
//        messager = processingEnv.getMessager();
//        messager.printMessage(Diagnostic.Kind.NOTE, "stone-->" + this + "init()");

        filer = processingEnv.getFiler();
        elements = processingEnv.getElementUtils();
        types = processingEnv.getTypeUtils();

        Map<String, String> options = processingEnv.getOptions();
        if (options != null) {
            moduleName = options.get(Consts.ARGUMENTS_NAME);
        }
        log.i("RouteProcessor Parameters:" + moduleName);

    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        return super.getCompletions(element, annotation, member, userText);
    }

    /*
     * 如下三个方法 都可以通过注解的方式
     * getSupportedAnnotationTypes getSupportedOptions getSupportedSourceVersion
     */
   /* @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Route.class.getCanonicalName());
    }

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }*/

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //获取 @Route 注解的元素 集合
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(Route.class);
        if (set != null && !set.isEmpty()) {
            processRoute(set);
        }
        return true;

//        for (TypeElement element : annotations) {
//            if (element.getQualifiedName().toString().equals(Route.class.getCanonicalName())) {}

//            messager.printMessage(Diagnostic.Kind.NOTE, "stone-->" + element.getQualifiedName());
//
//            FieldSpec fieldSpec = FieldSpec.builder(String.class, "testField", Modifier.PROTECTED)
//                    .build();
//            MethodSpec testMethodSpec = MethodSpec.methodBuilder("test")
//                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
//                    .returns(void.class)
//                    .addParameter(String[].class, "args")
//                    .addStatement("System.out.println($S)", "stone-->")
//                    .beginControlFlow("for (int i = 0; i < 5; i++)")
//                    .addStatement("")
//                    .endControlFlow()
//                    .build();
//            TypeSpec helloTypeSpec = TypeSpec.classBuilder("HelloWorldStone")
//                    .addMethod(testMethodSpec)
//                    .addField(fieldSpec)
//                    .build();
//            JavaFile javaFile = JavaFile.builder("com.stone", helloTypeSpec)
//                    .build();
//            try {
//                javaFile.writeTo(filer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
    }

    private void processRoute(Set<? extends Element> routeElements) {
        TypeElement activityTypeElement = elements.getTypeElement(Consts.ACTIVITY);
        TypeElement iServiceTypeElement = elements.getTypeElement(Consts.ISERVICE);

        for (Element element : routeElements) {
            RouteMeta routeMeta;

            TypeMirror typeMirror = element.asType(); //类信息
            log.i("类信息" + typeMirror.toString());

            Route route = element.getAnnotation(Route.class);
            //isSubtype(): 第1个参数是第2的子类型 类似 instanceOf

            if (types.isSubtype(typeMirror, activityTypeElement.asType())) {
                routeMeta = new RouteMeta(RouteMeta.Type.ACTIVITY, route, element);
            } else if (types.isSubtype(typeMirror, iServiceTypeElement.asType())) {
                routeMeta = new RouteMeta(RouteMeta.Type.ISERVICE, route, element);
            } else {
                throw new RuntimeException("[Just support] Activity Route：" + element);
            }

            // 检查是否配置 group 如果没有配置 则从path截取出组名； 初始化了 Map<String, List<RouteMeta>> groupMap
            categories(routeMeta);
        }

        TypeElement iRouteGroupTypeElement = elements.getTypeElement(Consts.IROUTE_GROUP);
        TypeElement iRouteRootTypeElement = elements.getTypeElement(Consts.IROUTE_ROOT);

        try {
            //生成 $$Group$$ 记录分组表
            generatedGroup(iRouteGroupTypeElement);

            //生成 $$Root$$类
            generatedRoot(iRouteRootTypeElement, iRouteGroupTypeElement);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建 groupTypeElement 对应类型的 实现类；生成 $$Group$$ 记录分组表
     * @param groupTypeElement  {@link Consts#IROUTE_GROUP}
     */
    private void generatedGroup(TypeElement groupTypeElement) throws IOException {
        /*
         * ParameterizedTypeName.get(ClassName rawType, TypeName... typeArguments)
         *      将typeArguments 类型参数，用于 rawType
         * 以下就是： Map<String, RouteMeta>
         */
        ParameterizedTypeName parameterizedType = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(RouteMeta.class)
        );

        //Map<String, RouteMeta> atlas
        ParameterSpec atlasSpec = ParameterSpec.builder(parameterizedType, "atlas").build();

        //遍历分组 每一个分组 创建一个 $$Group$$ 类
        for (Map.Entry<String, List<RouteMeta>> entry : groupMap.entrySet()) {
            //@Override public void loadInfo(Map<String, RouteMeta> atlas)
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(Consts.METHOD_LOAD_INTO)
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(atlasSpec);


            List<RouteMeta> groupList = entry.getValue();
            //将一组内，具体的路由信息 RouteMeta 添加到 map - atlas
            for (RouteMeta meta : groupList) {
                log.i("meta.getType(): " + meta.getType());
                // atlas.put("/group/path", RouteMeta.Type.ACTIVITY, );
                methodBuilder.addStatement(
                        "atlas.put($S, $T.build($T.$L, $T.class, $S, $S))",
                        meta.getPath(), //$S
                        ClassName.get(RouteMeta.class), //$T : RouteMeta
                        ClassName.get(RouteMeta.Type.class), //$T : RouteMeta.Type
                        meta.getType(), //$L : ACTIVITY
                        ClassName.get((TypeElement) meta.getElement()), //$T : 实际使用了 @Route 注解的类.class
                        meta.getPath(), //$S
                        meta.getGroup() //$S

                );
            }
            //类名
            String groupName = entry.getKey();
            String groupClassName = Consts.NAME_OF_GROUP + groupName;

            log.i("stone->" + groupClassName);
            TypeSpec typeSpec = TypeSpec.classBuilder(groupClassName)
                    .addSuperinterface(ClassName.get(groupTypeElement))
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodBuilder.build())
                    .build();

            JavaFile javaFile = JavaFile.builder(Consts.PACKAGE_OF_GENERATE_FILE, typeSpec).build();
            javaFile.writeTo(filer);

            rootMap.put(groupName, groupClassName);
        }
    }

    /**
     * 创建 rootTypeElement 对应实现类，生成 $$Root$$ 表，保存了各个分组的数据
     * @param rootTypeElement {@link Consts#IROUTE_ROOT}
     * @param groupTypeElement {@link Consts#IROUTE_GROUP}
     * @throws IOException
     */
    private void generatedRoot(TypeElement rootTypeElement, TypeElement groupTypeElement) throws IOException {
        /*
         * ParameterizedTypeName.get(ClassName rawType, TypeName... typeArguments)
         *      将typeArguments 类型参数，用于 rawType
         * WildcardTypeName.subtypeOf(TypeName upperBound)
         *      返回未知界限的通配符类型： ? extends upperBound
         *
         * 以下就是： Map<String, Class<? extends IRouteGroup>>
         */
        ParameterizedTypeName routesType = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ParameterizedTypeName.get(ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(groupTypeElement))
                )
        );

        //Map<String, Class<? extends IRouteGroup>> routes
        ParameterSpec routesSpec = ParameterSpec.builder(routesType, "routes")
                .build();
        //public void loadInfo(Map<String, Class<? extends IRouteGroup>> routes)
        MethodSpec.Builder loadIntoMethodOfRootBuilder = MethodSpec.methodBuilder(Consts.METHOD_LOAD_INTO)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(routesSpec);

        for (Map.Entry<String, String> entry : rootMap.entrySet()) {
            loadIntoMethodOfRootBuilder.addStatement("routes.put($S, $T.class)",
                    entry.getKey(),
                    ClassName.get(Consts.PACKAGE_OF_GENERATE_FILE, entry.getValue()));
        }

        //生成 $Root$类
        String rootClassName = Consts.NAME_OF_ROOT + moduleName;
        JavaFile.builder(Consts.PACKAGE_OF_GENERATE_FILE,
                TypeSpec.classBuilder(rootClassName)
                .addSuperinterface(ClassName.get(rootTypeElement))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(loadIntoMethodOfRootBuilder.build())
                .build()
        ).build().writeTo(filer);

        log.i("Generated RouteRoot: " + Consts.PACKAGE_OF_GENERATE_FILE + "." + rootClassName);
    }

    /**
     * 分组. 检查是否配置 group 如果没有配置 则从path截取出组名
     *
     * @param routeMeta
     */
    private void categories(RouteMeta routeMeta) {
        if (routeVerify(routeMeta)) {
            log.i("group :" + routeMeta.getGroup() + " path=" + routeMeta.getPath());
            List<RouteMeta> routeMetas = groupMap.get(routeMeta.getGroup());
            if (routeMetas == null) {
                routeMetas = new ArrayList<>();
                routeMetas.add(routeMeta);
                groupMap.put(routeMeta.getGroup(), routeMetas);
            } else {
                routeMetas.add(routeMeta);
            }
        } else {
            log.e("group info error.");
        }
    }

    /**
     * 验证地址的合法性
     *
     * @param routeMeta
     * @return
     */
    private boolean routeVerify(RouteMeta routeMeta) {
        String path = routeMeta.getPath();
        String group = routeMeta.getGroup();
        if (!path.startsWith("/")) {
            return false;
        }
        if (group == null || group.equals("")) {
            group = path.substring(1, path.indexOf("/", 1));
            if (group.equals("")) {
                return false;
            }
            routeMeta.setGroup(group);
        }
        return true;
    }


}
