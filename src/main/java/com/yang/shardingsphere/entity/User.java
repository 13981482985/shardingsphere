package com.yang.shardingsphere.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@TableName("t_user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    private String userName;
    private String password;
    private String idCart;

}
