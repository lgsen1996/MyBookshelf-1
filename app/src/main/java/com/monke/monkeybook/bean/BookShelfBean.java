//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.monke.monkeybook.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.monke.monkeybook.widget.contentview.BookContentView;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 书架item Bean
 */

@Entity
public class BookShelfBean implements Parcelable,Cloneable{
    @Transient
    public static final String LOCAL_TAG = "loc_book";
    @Transient
    private String errorMsg;
    @Transient
    private boolean isLoading;

    @Id
    private String noteUrl; //对应BookInfoBean noteUrl;
    private Integer durChapter = 0;   //当前章节 （包括番外）
    private Integer durChapterPage = BookContentView.DurPageIndexBegin;  // 当前章节位置   用页码
    private Long finalDate = System.currentTimeMillis();  //最后阅读时间
    private Boolean hasUpdate = false;  //是否有更新
    private Integer newChapters = 0;  //更新章节数
    private String tag;
    private Integer serialNumber = 0; //手动排序
    private Long finalRefreshData = System.currentTimeMillis();  //章节最后更新时间
    private Integer group = 0;
    private Boolean isScroll = false;

    @Transient
    private BookInfoBean bookInfoBean = new BookInfoBean();

    public BookShelfBean(){

    }

    @Transient
    public static final Creator<BookShelfBean> CREATOR = new Creator<BookShelfBean>() {
        @Override
        public BookShelfBean createFromParcel(Parcel in) {
            return new BookShelfBean(in);
        }

        @Override
        public BookShelfBean[] newArray(int size) {
            return new BookShelfBean[size];
        }
    };

    protected BookShelfBean(Parcel in) {
        noteUrl = in.readString();
        durChapter = in.readInt();
        durChapterPage = in.readInt();
        finalDate = in.readLong();
        tag = in.readString();
        bookInfoBean = in.readParcelable(BookInfoBean.class.getClassLoader());
        serialNumber = in.readInt();
        finalRefreshData = in.readLong();
        group = in.readInt();
        isScroll = in.readByte() != 0;
    }

    @Generated(hash = 1672419505)
    public BookShelfBean(String noteUrl, Integer durChapter, Integer durChapterPage,
            Long finalDate, Boolean hasUpdate, Integer newChapters, String tag,
            Integer serialNumber, Long finalRefreshData, Integer group, Boolean isScroll) {
        this.noteUrl = noteUrl;
        this.durChapter = durChapter;
        this.durChapterPage = durChapterPage;
        this.finalDate = finalDate;
        this.hasUpdate = hasUpdate;
        this.newChapters = newChapters;
        this.tag = tag;
        this.serialNumber = serialNumber;
        this.finalRefreshData = finalRefreshData;
        this.group = group;
        this.isScroll = isScroll;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteUrl);
        dest.writeInt(group);
        dest.writeInt(durChapter);
        dest.writeInt(durChapterPage);
        dest.writeLong(finalDate);
        dest.writeString(tag);
        dest.writeParcelable(bookInfoBean, flags);
        dest.writeInt(serialNumber);
        dest.writeLong(finalRefreshData);
        dest.writeByte((byte) (isScroll ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BookShelfBean bookShelfBean = (BookShelfBean) super.clone();
        bookShelfBean.noteUrl = noteUrl;
        bookShelfBean.tag = tag;
        bookShelfBean.bookInfoBean = (BookInfoBean) bookInfoBean.clone();
        return bookShelfBean;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    public int getDurChapter() {
        return durChapter;
    }

    public void setDurChapter(int durChapter) {
        this.durChapter = durChapter;
    }

    //获取当前章节
    public ChapterListBean getDurChapterListBean() {
        return getChapterList(durChapter);
    }

    //获取最新章节
    public ChapterListBean getLastChapterListBean() {
        return getChapterList(bookInfoBean.getChapterList().size() - 1);
    }

    public ChapterListBean getChapterList(int index) {

        if (getChapterList() == null || getChapterListSize() == 0 || index < 0) {
            ChapterListBean chapterListBean = new ChapterListBean();
            chapterListBean.setDurChapterName("暂无");
            chapterListBean.setDurChapterUrl("暂无");
            return chapterListBean;
        } else if (index < getChapterListSize()) {
            return getChapterList().get(index);
        } else {
            durChapter = getChapterListSize() - 1;
            return getChapterList().get(getChapterListSize() - 1);
        }
    }

    public List<ChapterListBean> getChapterList() {
        return getBookInfoBean().getChapterList();
    }

    public int getChapterListSize() {
        return getChapterList().size();
    }

    public int getDurChapterPage() {
        return durChapterPage;
    }

    public void setDurChapterPage(int durChapterPage) {
        this.durChapterPage = durChapterPage;
    }

    public long getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(long finalDate) {
        this.finalDate = finalDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BookInfoBean getBookInfoBean() {
        return bookInfoBean;
    }

    public void setBookInfoBean(BookInfoBean bookInfoBean) {
        this.bookInfoBean = bookInfoBean;
    }

    public boolean getHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public int getNewChapters() {
        return newChapters;
    }

    public void setNewChapters(int newChapters) {
        this.newChapters = newChapters;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getFinalRefreshData() {
        return this.finalRefreshData;
    }

    public void setFinalRefreshData(long finalRefreshData) {
        this.finalRefreshData = finalRefreshData;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public int getGroup() {
        return this.group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Boolean getIsScroll() {
        return this.isScroll;
    }

    public void setIsScroll(Boolean isScroll) {
        this.isScroll = isScroll;
    }

    public void setDurChapter(Integer durChapter) {
        this.durChapter = durChapter;
    }

    public void setDurChapterPage(Integer durChapterPage) {
        this.durChapterPage = durChapterPage;
    }

    public void setFinalDate(Long finalDate) {
        this.finalDate = finalDate;
    }

    public void setHasUpdate(Boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public void setNewChapters(Integer newChapters) {
        this.newChapters = newChapters;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setFinalRefreshData(Long finalRefreshData) {
        this.finalRefreshData = finalRefreshData;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
}