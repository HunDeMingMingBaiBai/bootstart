package com.ssd.start.pojo;

import lombok.Data;

/**
 * @author WHD
 * @date 2020/7/23 15:55
 */
@Data
public class ResultObject {

    private Integer code;

    private Object data;

    public ResultObject(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static ResultObject success(Object data){
        return new ResultObject(CODE.OK.getNum(), data);
    }

    public static ResultObject error(Object data){
        return new ResultObject(CODE.ERROR.getNum(), data);
    }

    enum CODE {
        OK(200, ""),
        ERROR(500, "出错")
        ;

        private Integer num;

        private String name;

        CODE(Integer num, String name) {
            this.num = num;
            this.name = name;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
