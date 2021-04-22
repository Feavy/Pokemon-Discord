package fr.reminy.pokemon_discord.annotation.command;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class CommandProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Register.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements1 = roundEnv.getElementsAnnotatedWith(Register.class);
        if(elements1.isEmpty()) {
            return true;
        }
        Set<TypeElement> elements = (Set<TypeElement>) elements1;

        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile("fr.reminy.pokemon_discord.command.Commands");
            PrintWriter writer = new PrintWriter(sourceFile.openWriter());

            writer.println("package fr.reminy.pokemon_discord.command;");
            writer.println("");
            writer.println("import java.util.Map;");
            writer.println("import java.util.HashMap;");
            writer.println("import java.util.Set;");
            writer.println("import java.util.HashSet;");
            writer.println("import java.util.List;");
            writer.println("import java.util.ArrayList;");
            writer.println("import java.util.Optional;");
            writer.println("");
            writer.println("public class Commands {");
            writer.println("");
            writer.println("    private static Map<String, Command> map = new HashMap<>();");
            writer.println("    private static Set<Command> set = new HashSet<>();");
            writer.println("");
            writer.println("    static {");
            for (TypeElement element : elements) {
            writer.println("        register(new "+element.getQualifiedName()+"());");
            }
            writer.println("    }");
            writer.println("");
            writer.println("    private static void register(Command command) {");
            writer.println("        for(String lbl : command.getLabels()) {");
            writer.println("            map.put(lbl, command);");
            writer.println("            set.add(command);");
            writer.println("        }");
            writer.println("    }");
            writer.println("");
            writer.println("    public static Optional<Command> get(String label) {");
            writer.println("        return Optional.ofNullable(map.get(label));");
            writer.println("    }");
            writer.println("");
            writer.println("    public static List<Command> list() {");
            writer.println("        return new ArrayList<>(set);");
            writer.println("    }");
            writer.println("");
            writer.println("    public static Map<String, Command> map() {");
            writer.println("        return map;");
            writer.println("    }");
            writer.println("");
            writer.println("}");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
