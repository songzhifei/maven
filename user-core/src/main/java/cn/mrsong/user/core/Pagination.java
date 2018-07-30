package cn.mrsong.user.core;

public class Pagination {
	public int pageIndex;
	public int pageSize;
	public int getStart(){
		return pageIndex;
	}
	public int getEnd(){
		return this.pageIndex + this.pageSize;
	}
	public void setDataSize(int pageSize){
		this.pageSize = pageSize;
	}
}
