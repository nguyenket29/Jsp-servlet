package service;

import sort.Sorter;

public interface Pageble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSort();
}
