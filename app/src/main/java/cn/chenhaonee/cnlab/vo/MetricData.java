package cn.chenhaonee.cnlab.vo;

/**
 * Created by chenhaonee on 2017/7/5.
 */

public class MetricData {
    private String git_url;
    private boolean measured;
    private int total_line_count;
    private int comment_line_count;
    private int field_count;
    private int method_count;
    private int max_coc;

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public boolean isMeasured() {
        return measured;
    }

    public void setMeasured(boolean measured) {
        this.measured = measured;
    }

    public int getTotal_line_count() {
        return total_line_count;
    }

    public void setTotal_line_count(int total_line_count) {
        this.total_line_count = total_line_count;
    }

    public int getComment_line_count() {
        return comment_line_count;
    }

    public void setComment_line_count(int comment_line_count) {
        this.comment_line_count = comment_line_count;
    }

    public int getField_count() {
        return field_count;
    }

    public void setField_count(int field_count) {
        this.field_count = field_count;
    }

    public int getMethod_count() {
        return method_count;
    }

    public void setMethod_count(int method_count) {
        this.method_count = method_count;
    }

    public int getMax_coc() {
        return max_coc;
    }

    public void setMax_coc(int max_coc) {
        this.max_coc = max_coc;
    }
}
