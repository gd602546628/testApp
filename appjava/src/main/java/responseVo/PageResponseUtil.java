package responseVo;

import com.github.pagehelper.PageInfo;


public class PageResponseUtil {

    public static PageResponseVo success(PageInfo pageInfo) {
        PageResponseVo pageResponseVo = new PageResponseVo();
        pageResponseVo.setList(pageInfo.getList());
        pageResponseVo.setPageNum(pageInfo.getPageNum());
        pageResponseVo.setPageSize(pageInfo.getPageSize());
        pageResponseVo.setTotalCount(pageInfo.getTotal());
        pageResponseVo.setTotalPage(pageInfo.getPages());
        // return pageResponseVo.toString();
        return pageResponseVo;
    }
}
