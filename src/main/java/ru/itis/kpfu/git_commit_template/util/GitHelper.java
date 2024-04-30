package ru.itis.kpfu.git_commit_template.util;

import java.util.List;

import git4idea.GitBranch;
import git4idea.GitLocalBranch;
import git4idea.GitUtil;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitHelper {

    private static final Logger logger = LoggerFactory.getLogger(GitHelper.class);

    public static @Nullable GitRepository getGitRepository(@Nullable Project project) {
        if (project == null) return null;

        GitRepositoryManager manager = GitUtil.getRepositoryManager(project);
        GitRepository repository = manager.getRepositoryForRootQuick(project.getBaseDir());

        // in the case where the base dir of the Git repo and the base dir of IDEA project don't match this can be null
        if (repository == null) {
            final List<GitRepository> repos = manager.getRepositories();
            if (repos.size() > 0) {
                repository = repos.get(0);
                if (repos.size() > 1) {
                    logger.warn("More than 1 Git repo was found. Defaulting to the first returned: " + repository.getRoot().getPath());
                }
            } else {
                logger.warn("We are in a Git project that does not have any Git repos. (We may be asking too early.)");
            }
        }

        return repository;
    }

    public static String getBranchName(@Nullable GitRepository repository) {
        if (repository == null) return null;

        GitLocalBranch branch = repository.getCurrentBranch();
        if (branch != null) {
            return branch.getName();
        } else {
            return null;
        }
    }
}
