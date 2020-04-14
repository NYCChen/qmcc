package com.qmcc.sys.vo;

import com.qmcc.sys.domain.Loginfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoWeVo extends Loginfo {

    private static final long serialVersionUID = 1L;

    private Integer[] ids;//接收多个ID

}
