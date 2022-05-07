package top.iaminlearn.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;
    public static Result succ(Object data) {
        return Result.succ("200","操作成功",data);
       /* Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");
        return m;*/
    }
     public static Result succ(String code,String msg, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }
    public static Result fail(String msg) {
        return Result.fail("400",msg,null);
//        Result m = new Result();
//        m.setCode("-1");
//        m.setData(null);
//        m.setMsg(mess);
//        return m;
    }
    public static Result fail(String code, String msg, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }
}
