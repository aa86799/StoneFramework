package com.stone.lib.compilse;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-13 08:10
 */
@AutoService(Processor.class)
//支持处理的注解
@SupportedAnnotationTypes({"com.stone.lib.annotations.router.Route"})
//@SupportedOptions("com.stone.lib.annotations.router.Route")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TestProcessor extends AbstractProcessor {

    private Messager messager;
    private Filer filer;

    /*
     * 会自动被注解处理工具调用，并传入ProcessingEnviroment参数，通过该参数可以获取到很多有用的工具类: Elements , Types , Filer 等等
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "stone-->" + this + "init()");

        filer = processingEnv.getFiler();
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
        for (TypeElement element : annotations) {
            messager.printMessage(Diagnostic.Kind.NOTE, "stone-->" + element.getQualifiedName());

            FieldSpec fieldSpec = FieldSpec.builder(String.class, "testField", Modifier.PROTECTED)
                    .build();
            MethodSpec testMethodSpec = MethodSpec.methodBuilder("test")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("System.out.println($S)", "stone-->")
                    .beginControlFlow("for (int i = 0; i < 5; i++)")
                    .addStatement("")
                    .endControlFlow()
                    .build();
            TypeSpec helloTypeSpec = TypeSpec.classBuilder("HelloWorldStone")
                    .addMethod(testMethodSpec)
                    .addField(fieldSpec)
                    .build();
            JavaFile javaFile = JavaFile.builder("com.stone", helloTypeSpec)
                    .build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


}
