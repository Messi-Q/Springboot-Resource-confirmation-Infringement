package team.educoin.transaction.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("资源文件实体")
public class FileInfo {
    @ApiModelProperty(value = "资源文件id")
    private String id;
    private String fileOwner;
    private String fileInitialProvider;
    private String fileTitle;
    private String fileImage;
    private String fileDescription;
    private Double fileReadPrice;
    private Double fileOwnerShipPrice;
    private String fileName;
    private String fileKeyWord;
    private String fileContentType;
    private String fileFormat;
    private String fileSize;
    private Integer fileChecked;

    public FileInfo(){}

    public FileInfo(String id, String fileOwner) {
        this.id = id;
        this.fileOwner = fileOwner;
    }

    public FileInfo(String id, String fileOwner, String fileInitialProvider, String fileTitle,
                    String fileImage, String fileDescription, Double fileReadPrice,
                    Double fileOwnerShipPrice, String fileName, String fileKeyWord,
                    String fileContentType, String fileFormat, String fileSize) {
        this.id = id;
        this.fileOwner = fileOwner;
        this.fileInitialProvider = fileInitialProvider;
        this.fileTitle = fileTitle;
        this.fileImage = fileImage;
        this.fileDescription = fileDescription;
        this.fileReadPrice = fileReadPrice;
        this.fileOwnerShipPrice = fileOwnerShipPrice;
        this.fileName = fileName;
        this.fileKeyWord = fileKeyWord;
        this.fileContentType = fileContentType;
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public FileInfo(String id, String fileOwner, String fileTitle, String fileImage,
                    String fileDescription, Double fileReadPrice, Double fileOwnerShipPrice,
                    String fileKeyWord, String fileContentType) {
        this.id = id;
        this.fileOwner = fileOwner;
        this.fileTitle = fileTitle;
        this.fileImage = fileImage;
        this.fileDescription = fileDescription;
        this.fileReadPrice = fileReadPrice;
        this.fileOwnerShipPrice = fileOwnerShipPrice;
        this.fileKeyWord = fileKeyWord;
        this.fileContentType = fileContentType;
    }

    public FileInfo(String id, String fileOwner, String fileInitialProvider,
                    String fileTitle, String fileImage, String fileDescription,
                    Double fileReadPrice, Double fileOwnerShipPrice, String fileName,
                    String fileKeyWord, String fileContentType, String fileFormat, String fileSize,
                    Integer fileChecked) {
        this.id = id;
        this.fileOwner = fileOwner;
        this.fileInitialProvider = fileInitialProvider;
        this.fileTitle = fileTitle;
        this.fileImage = fileImage;
        this.fileDescription = fileDescription;
        this.fileReadPrice = fileReadPrice;
        this.fileOwnerShipPrice = fileOwnerShipPrice;
        this.fileName = fileName;
        this.fileKeyWord = fileKeyWord;
        this.fileContentType = fileContentType;
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
        this.fileChecked = fileChecked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFileInitialProvider() {
        return fileInitialProvider;
    }

    public void setFileInitialProvider(String fileInitialProvider) {
        this.fileInitialProvider = fileInitialProvider;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Double getFileReadPrice() {
        return fileReadPrice;
    }

    public void setFileReadPrice(Double fileReadPrice) {
        this.fileReadPrice = fileReadPrice;
    }

    public Double getFileOwnerShipPrice() {
        return fileOwnerShipPrice;
    }

    public void setFileOwnerShipPrice(Double fileOwnerShipPrice) {
        this.fileOwnerShipPrice = fileOwnerShipPrice;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileKeyWord() {
        return fileKeyWord;
    }

    public void setFileKeyWord(String fileKeyWord) {
        this.fileKeyWord = fileKeyWord;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileChecked() {
        return fileChecked;
    }

    public void setFileChecked(Integer fileChecked) {
        this.fileChecked = fileChecked;
    }
}
