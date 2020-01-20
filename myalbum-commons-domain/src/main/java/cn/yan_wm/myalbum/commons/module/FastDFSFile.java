package cn.yan_wm.myalbum.commons.module;

/**
 * @program: fastDFSDemo
 * @description: FastDFSFile Module
 * @author: yan_zt
 * @create: 2019-12-27 14:33
 */
public class FastDFSFile {
    private String fileName; //文件名

    private String base64; //图片base64编码

    private String ext; //扩展名

    private String author; //作者

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
