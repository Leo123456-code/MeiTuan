package com.imooc.requset;

import lombok.Data;

/**
 * ClassName: PageQuery
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-15:26
 * email 1437665365@qq.com
 */
@Data
public class PageQuery {

    private Integer page = 1;

    private Integer size = 6;
}
