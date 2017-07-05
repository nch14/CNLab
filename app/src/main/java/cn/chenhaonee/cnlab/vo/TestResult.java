package cn.chenhaonee.cnlab.vo;

import java.util.List;

/**
 * Created by chenhaonee on 2017/7/5.
 */

public class TestResult {
    private String git_url;
    private boolean compile_succeeded;
    private boolean tested;
    private List<TestCase> testcases;

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public boolean isCompile_succeeded() {
        return compile_succeeded;
    }

    public void setCompile_succeeded(boolean compile_succeeded) {
        this.compile_succeeded = compile_succeeded;
    }

    public boolean isTested() {
        return tested;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    public List<TestCase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<TestCase> testcases) {
        this.testcases = testcases;
    }
}
