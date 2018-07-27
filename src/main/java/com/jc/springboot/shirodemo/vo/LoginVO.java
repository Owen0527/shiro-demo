package com.jc.springboot.shirodemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * LoginVO
 *
 * @author OwenZhu
 * @date 18/7/25
 */
@ApiModel("登录VO")
@Data
public class LoginVO extends BaseVO {

    @ApiModelProperty("登录账户")
    private String loginName;

    @ApiModelProperty("密码")
    private String pw;
}
