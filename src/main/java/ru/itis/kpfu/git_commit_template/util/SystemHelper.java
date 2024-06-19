package ru.itis.kpfu.git_commit_template.util;

import ru.itis.kpfu.git_commit_template.models.Argument;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemHelper {

    public static List<Argument> getArgumentList(Map<String, String> args) {
        return args.entrySet().stream()
                .map(x -> new Argument(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }
}
