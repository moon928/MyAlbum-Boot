package cn.yan_wm.myalbum.commons.module;

/**
 * @program: fastDFSDemo
 * @description: FastDFSFile Module
 * @author: yan_zt
 * @create: 2019-12-27 14:33
 */
public class FastDFSFile {
    //文件名
    private String fileName;
    //图片base64编码
    private String base64;
    //扩展名
    private String ext;
    //作者
    private String author;

    //文件所在服务器的组名
    private String groupName;
    //文件id
    private String fileId;

    //上传到的相册id
    private Integer albumId;

    private Integer image_id;

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }


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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
