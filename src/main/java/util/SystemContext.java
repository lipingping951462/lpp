package util;

public class SystemContext {

	public static ThreadLocal<Integer> pagenoLocal = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> pagesizeLocal = new ThreadLocal<Integer>();

	public static int getPagenoLocal() {
		Integer pageno=pagenoLocal.get();
		if(pageno==null){
			pageno=1;
		}
		return pageno;
	}

	public static void setPagenoLocal(int pageNo) {
		pagenoLocal.set(pageNo);
	}
	
	public static void removePageNo(){
		pagenoLocal.remove();
	}

	public static int getPagesizeLocal() {
		Integer pagesize=pagesizeLocal.get();
		if(null==pagesize){
			pagesize=10;
		}
		return pagesize;
	}

	public static void setPagesizeLocal(int pageSize) {
		pagesizeLocal.set(pageSize);
	}
	
	public static void removePageSize(){
		pagesizeLocal.remove();
	}

}
