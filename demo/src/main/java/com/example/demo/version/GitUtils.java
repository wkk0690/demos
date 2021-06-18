package com.example.demo.version;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
public class GitUtils {

    /**
     * git仓路径
     */
    final static String patch = "/opt/webapps/blog/.git";

    /**
     * 代码分支
     */
    final static String branch = "origin/master";

    public static void main(String[] args) {
//        doClone(); //克隆
//        doPull(); //拉取
//        doReset(); //重置
        doRevert(); //还原
//        doStatus(); //状态
    }

    /**
     * 拉取
     *
     * @return
     */
    public static String doPull() {
        String result;
        Repository repo = null;
        try {
            repo = new FileRepository(new File(patch));
            Git git = new Git(repo);

            log.info("开始重置");
            //重置
            git.reset()
                    .setMode(ResetCommand.ResetType.HARD)
                    .setRef(branch).call();

            log.info("开始拉取");

            //拉取
            git.pull()
                    .setRemote("origin")
                    .setRemoteBranchName("gh-pages")
                    .call();
            result = "拉取成功!";
            log.info(result);
        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            if (repo != null) {
                repo.close();
            }
        }
        return result;
    }

    /**
     * 重置
     *
     * @return
     */
    public static String doReset() {
        String result;

        Repository repo = null;
        try {
            repo = new FileRepository(new File(patch));
            Git git = new Git(repo);
            git.reset().setMode(ResetCommand.ResetType.HARD).setRef(branch).call();
            result = "重置成功!";

        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            if (repo != null) {
                repo.close();
            }
        }
        return result;
    }

    /**
     * 恢复
     */
    public static String doRevert() {
        String result;

        Repository repo = null;
        try {
            repo = new FileRepository(new File(patch));
            Git git = new Git(repo);
            git.revert().call();
            result = "恢复成功!";

        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            if (repo != null) {
                repo.close();
            }
        }
        return result;
    }

    /**
     * 克隆
     *
     * @return
     */
    public static String doClone() {
        String result;
        try {
            Git.cloneRepository()
                    .setURI("https://gitee.com/WKK0690/parking.git")
                    .setDirectory(new File("d:/parking"))
                    .call();
            result = "克隆成功了!";
        } catch (GitAPIException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 状态
     */
    public static void doStatus() {
        File RepoGitDir = new File("/blog/.git");
        Repository repo = null;
        try {
            repo = new FileRepository(RepoGitDir.getAbsolutePath());
            Git git = new Git(repo);
            Status status = git.status().call();
            log.info("Git Change: " + status.getChanged());
            log.info("Git Modified: " + status.getModified());
            log.info("Git UncommittedChanges: " + status.getUncommittedChanges());
            log.info("Git Untracked: " + status.getUntracked());
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (repo != null) {
                repo.close();
            }
        }
    }
}