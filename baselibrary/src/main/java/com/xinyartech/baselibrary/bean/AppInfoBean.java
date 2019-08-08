package com.xinyartech.baselibrary.bean;

/**
 * Created by zhangjiebo on 2019/1/17.
 *
 * @author ZhangJieBo
 */
public class AppInfoBean {
    /**
     * code : 200
     * data : {"lastVersion":{"description":"版本更新","file_name":"雅快配送骑手端yakuai-19012701.apk","id":2,"ip":"117.30.72.134","sys_app_id":"6","uploading_time":"2019-01-27 14:38:09","url":"http://version.app.xinyartech.com/apkstorage/2019/01/27/8E0738D1AC10BD6A5D7230015F954C65.apk","version":"19012701","version_name":"1.1.3"},"project":{"app_name_id":"ps_dev","appid":"com.xinyartech.knight","branch":"dev_001","create_time":"2019-01-16 16:48:09","env":"dev","id":6,"name":"雅快配送骑手端测试版-android","params":"[{\"paramName\":\"野狗推送\",\"paramKey\":\"Wilddog_URl\",\"paramValue\":\"https://wd2313347259aylqzm.wilddogio.com/test/\"},{\"paramName\":\"野狗推送节点\",\"paramKey\":\"Wilddog_BEFSUF\",\"paramValue\":\"/test/\"},{\"paramName\":\"服务host\",\"paramKey\":\"SERVER_URl\",\"paramValue\":\"http://test.ps.xinyartech.com/\"}]"}}
     * message : success
     */

    private int code;
    private DataBean data;
    private String message;

    public AppInfoBean(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * lastVersion : {"description":"版本更新","file_name":"雅快配送骑手端yakuai-19012701.apk","id":2,"ip":"117.30.72.134","sys_app_id":"6","uploading_time":"2019-01-27 14:38:09","url":"http://version.app.xinyartech.com/apkstorage/2019/01/27/8E0738D1AC10BD6A5D7230015F954C65.apk","version":"19012701","version_name":"1.1.3"}
         * project : {"app_name_id":"ps_dev","appid":"com.xinyartech.knight","branch":"dev_001","create_time":"2019-01-16 16:48:09","env":"dev","id":6,"name":"雅快配送骑手端测试版-android","params":"[{\"paramName\":\"野狗推送\",\"paramKey\":\"Wilddog_URl\",\"paramValue\":\"https://wd2313347259aylqzm.wilddogio.com/test/\"},{\"paramName\":\"野狗推送节点\",\"paramKey\":\"Wilddog_BEFSUF\",\"paramValue\":\"/test/\"},{\"paramName\":\"服务host\",\"paramKey\":\"SERVER_URl\",\"paramValue\":\"http://test.ps.xinyartech.com/\"}]"}
         */

        private LastVersionBean lastVersion;
        private ProjectBean project;

        public DataBean(ProjectBean project) {
            this.project = project;
        }

        public LastVersionBean getLastVersion() {
            return lastVersion;
        }

        public void setLastVersion(LastVersionBean lastVersion) {
            this.lastVersion = lastVersion;
        }

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public static class LastVersionBean {
            /**
             * description : 版本更新
             * file_name : 雅快配送骑手端yakuai-19012701.apk
             * id : 2
             * ip : 117.30.72.134
             * sys_app_id : 6
             * uploading_time : 2019-01-27 14:38:09
             * url : http://version.app.xinyartech.com/apkstorage/2019/01/27/8E0738D1AC10BD6A5D7230015F954C65.apk
             * version : 19012701
             * version_name : 1.1.3
             */

            private String description;
            private String file_name;
            private int id;
            private String ip;
            private String sys_app_id;
            private String uploading_time;
            private String url;
            private String version;
            private String version_name;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getSys_app_id() {
                return sys_app_id;
            }

            public void setSys_app_id(String sys_app_id) {
                this.sys_app_id = sys_app_id;
            }

            public String getUploading_time() {
                return uploading_time;
            }

            public void setUploading_time(String uploading_time) {
                this.uploading_time = uploading_time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getVersion_name() {
                return version_name;
            }

            public void setVersion_name(String version_name) {
                this.version_name = version_name;
            }
        }

        public static class ProjectBean {
            /**
             * app_name_id : ps_dev
             * appid : com.xinyartech.knight
             * branch : dev_001
             * create_time : 2019-01-16 16:48:09
             * env : dev
             * id : 6
             * name : 雅快配送骑手端测试版-android
             * params : [{"paramName":"野狗推送","paramKey":"Wilddog_URl","paramValue":"https://wd2313347259aylqzm.wilddogio.com/test/"},{"paramName":"野狗推送节点","paramKey":"Wilddog_BEFSUF","paramValue":"/test/"},{"paramName":"服务host","paramKey":"SERVER_URl","paramValue":"http://test.ps.xinyartech.com/"}]
             */

            private String app_name_id;
            private String appid;
            private String branch;
            private String create_time;
            private String env;
            private int id;
            private String name;
            private String params;

            public ProjectBean(String params) {
                this.params = params;
            }

            public String getApp_name_id() {
                return app_name_id;
            }

            public void setApp_name_id(String app_name_id) {
                this.app_name_id = app_name_id;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getBranch() {
                return branch;
            }

            public void setBranch(String branch) {
                this.branch = branch;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getEnv() {
                return env;
            }

            public void setEnv(String env) {
                this.env = env;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }
        }
    }
}
