package service.Impl;

import service.Pageble;
import sort.Sorter;

public class PageRequest implements Pageble {
    private Integer page,maxPageItem;
    private Sorter sort;

    public PageRequest(Integer page, Integer maxPageItem , Sorter sort){
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.sort = sort;
    }

    public Sorter getSort() {
        if(this.sort != null){
            return this.sort;
        }
        return null;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        Integer offset = (this.page - 1) * this.maxPageItem ;
        if (this.page != null && this.maxPageItem != null){
            return offset;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItem;
    }
}
