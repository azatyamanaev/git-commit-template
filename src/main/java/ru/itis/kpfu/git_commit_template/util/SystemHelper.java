package ru.itis.kpfu.git_commit_template.util;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.itis.kpfu.git_commit_template.models.Argument;

public class SystemHelper {

    public static Map<String, String> getGitSystemVars() {

        Map<String, String> vars = System.getenv();
        return vars.entrySet().stream()
                .filter(x -> x.getKey().startsWith("GITT_") || List.of("OS", "COMPUTERNAME").contains(x.getKey()))
                .map(x -> new AbstractMap.SimpleEntry<>(x.getKey().replaceAll("GITT_", ""), x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Argument> getArgumentList(Map<String, String> args) {
        return args.entrySet().stream()
                   .map(x -> new Argument(x.getKey(), x.getValue()))
                   .collect(Collectors.toList());
    }
}
