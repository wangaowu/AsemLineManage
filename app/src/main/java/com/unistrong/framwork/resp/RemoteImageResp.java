package com.unistrong.framwork.resp;

import java.util.List;

/**
 * 远程图片地址
 */
public class RemoteImageResp {

    private DataBean data;
    private int failure;
    private int resultCode;
    private int success;
    private int time;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        private List<?> audios;
        private List<ImagesBean> images;
        private List<?> others;
        private List<?> videos;

        public List<?> getAudios() {
            return audios;
        }

        public void setAudios(List<?> audios) {
            this.audios = audios;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public List<?> getOthers() {
            return others;
        }

        public void setOthers(List<?> others) {
            this.others = others;
        }

        public List<?> getVideos() {
            return videos;
        }

        public void setVideos(List<?> videos) {
            this.videos = videos;
        }

        public static class ImagesBean {
            /**
             * oFileName : IMG_20180614_101619.jpg
             * oUrl : http://192.168.116.90:8028/u/0/0/201806/o/d8395aa419fb469486970de39badf840.jpg
             * status : 1
             * tUrl : http://192.168.116.90:8028/u/0/0/201806/t/d8395aa419fb469486970de39badf840.jpg
             */

            private String oFileName;
            private String oUrl;
            private int status;
            private String tUrl;

            public String getOFileName() {
                return oFileName;
            }

            public void setOFileName(String oFileName) {
                this.oFileName = oFileName;
            }

            public String getOUrl() {
                return oUrl;
            }

            public void setOUrl(String oUrl) {
                this.oUrl = oUrl;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTUrl() {
                return tUrl;
            }

            public void setTUrl(String tUrl) {
                this.tUrl = tUrl;
            }
        }
    }
}
