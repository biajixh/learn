	/**
	 * 获取管理机构中职称晋升者信息列表(分页)
	 * @param page 页码
	 * @param name 搜索框填写的姓名
	 * @param jcdwbm 部门编码
	 * @param gwbdbs 岗位是否有变动记录 0否，1是
	 * @param gljg_lb 管理机构类别
	 * @return Page<RsglYwJbqkModel>
	 */
	public Page<RsglYwJbqkModel> getZcjsUserList(int page, String name,String dwbm,String jcdwbm,String pzrq, int gljg_lb) {
		Record params = new Record();
		Integer index = 0;
		String sql = "select f.gljg_mc as jbqk_jcdwmc,a.jbqk_id, a.jbqk_xm, a.jbqk_zjbh,g.name as zcbd_xzcmc,c.zcbd_xzcqdrq ";
		String fromstr = "FROM rsgl_yw_jbqk a " + 
				" inner join rsgl_yw_pyxx b on a.Jbqk_xpyjlh = b.pyxx_bh and b.pyxx_ryid = a.jbqk_id " + 
				" inner join rsgl_yw_zcbd c on a.jbqk_xzcjlh = c.zcbd_bh and c.zcbd_ryid = a.jbqk_id " + 
				" inner join gzgl_cs_zwgz d on d.zwgz_zxny=201607 "+ 
						" and d.zwgz_zwdj= decode(substr(c.zcbd_xzc,3,1) , '1', '2214', '2', '2223', '3', '2233', '4', '2242', '', '2251') "+
				" inner join gzgl_cs_zwgz e on e.zwgz_zxny=201607 "+
						" and e.zwgz_zwdj= decode(substr(b.pyxx_xgwdj,3,1) , '1', '2214', '2', '2223', '3', '2233', '4', '2242', '', '2251') "+
				" inner join gg_xt_gljg f on a.jbqk_jcdwbm = f.gljg_bm "+
				" inner join gg_dm_jszw g on c.zcbd_xzc = g.code "+
				" where (pyxx_sjtf=1 or substr(pyxx_xgwdj,1,2)='22') and d.zwgz_zwgz > e.zwgz_zwgz " + 
						" and a.jbqk_id not in (select pyxx_ryid from rsgl_yw_pyxx where Pyxx_baf = 0 or Pyxx_baf is null) ";
		if(gljg_lb == 4 || gljg_lb == 5) {//单位或二级单位查询
			fromstr += " and a.jbqk_dwbm = ? ";
			params.set(index.toString(), dwbm);
			index += 1;
			if(!jcdwbm.equals("0")) {
				fromstr += "and a.jbqk_jcdwbm = ? ";
				params.set(index.toString(), jcdwbm);
				index += 1;
			}
		}else if(gljg_lb == 6) {//部门查询
			fromstr += " and a.jbqk_jcdwbm = ? ";
			params.set(index.toString(), jcdwbm);
			index += 1;
		}else {
			return null;
		}
		
		if(!("").equals(name)) {	
			fromstr += " and a.jbqk_xm like ? ";
			params.set(index.toString(), "%"+name+"%");
			index += 1;
		}
		if(!("").equals(pzrq)) {
			fromstr += " and c.zcbd_xzcqdrq > ? ";
			params.set(index.toString(), pzrq);
		}
		fromstr += " order by a.jbqk_jcdwbm, a.jbqk_id ";
		return this.paginate(page,MainConfig.pgRcdNum,sql,fromstr,params.getColumnValues());
	}
	/**
	 * 获取管理机构中职称晋升者信息列表
	 * @param dwbm 单位编码
	 * @param jcdwbm 部门编码
	 * @param gljg_lb 管理机构类别
	 * @return List<RsglYwJbqkModel>
	 */
	public List<RsglYwJbqkModel> getZcjsUserList(String dwbm,String jcdwbm, int gljg_lb) {
		Record params = new Record();
		String index = "0";
		String sql = "select f.gljg_mc as jbqk_jcdwmc,a.jbqk_id, a.jbqk_xm, a.jbqk_zjbh,a.jbqk_xpyjlh ";
		String fromstr = "FROM rsgl_yw_jbqk a " + 
				" inner join rsgl_yw_pyxx b on a.Jbqk_xpyjlh = b.pyxx_bh and b.pyxx_ryid = a.jbqk_id " + 
				" inner join rsgl_yw_zcbd c on a.jbqk_xzcjlh = c.zcbd_bh and c.zcbd_ryid = a.jbqk_id " + 
				" inner join gzgl_cs_zwgz d on d.zwgz_zxny=201607 "+ 
						" and d.zwgz_zwdj= decode(substr(c.zcbd_xzc,3,1) , '1', '2214', '2', '2223', '3', '2233', '4', '2242', '', '2251') "+
				" inner join gzgl_cs_zwgz e on e.zwgz_zxny=201607 "+
						" and e.zwgz_zwdj= decode(substr(b.pyxx_xgwdj,3,1) , '1', '2214', '2', '2223', '3', '2233', '4', '2242', '', '2251') "+
				" inner join gg_xt_gljg f on a.jbqk_jcdwbm = f.gljg_bm "+
				" where (pyxx_sjtf=1 or substr(pyxx_xgwdj,1,2)='22') and d.zwgz_zwgz > e.zwgz_zwgz " + 
						" and a.jbqk_id not in (select pyxx_ryid from rsgl_yw_pyxx where Pyxx_baf = 0 or Pyxx_baf is null) ";
		if(gljg_lb == 4 || gljg_lb == 5) {//单位或二级单位查询
			fromstr += " and a.jbqk_dwbm = ? ";
			params.set(index, dwbm);
			index = "1";
			if(!jcdwbm.equals("0")) {
				fromstr += "and a.jbqk_jcdwbm = ? ";
				params.set(index, jcdwbm);
				index = "2";
			}
		}else if(gljg_lb == 6) {//部门查询
			fromstr += " and a.jbqk_jcdwbm = ? ";
			index = "1";
		}else {
			return null;
		}
		
		fromstr += " order by a.jbqk_jcdwbm, a.jbqk_id ";
		return this.find(sql+fromstr,params.getColumnValues());
	}
	
	/**
	 * 获取管理机构中职务晋升者信息列表(分页)
	 * @param page 页码
	 * @param name 搜索框填写的姓名
	 * @param dwbm 单位编码
	 * @param jcdwbm 部门编码
	 * @param gljg_lb 管理机构类别
	 * @return Page<RsglYwJbqkModel>
	 */
	public Page<RsglYwJbqkModel> getZwjsUserList(int page, String name,String dwbm,String jcdwbm,String pzrq, int gljg_lb) {
		Record params = new Record();
		Integer index = 0;
		String sql = "select f.gljg_mc as jbqk_jcdwmc,a.jbqk_id, a.jbqk_xm, a.jbqk_zjbh,g.name as zwbd_xzwjbmc,c.zwbd_pzrq ";
		String fromstr = "FROM rsgl_yw_jbqk a " + 
				" inner join rsgl_yw_pyxx b on a.Jbqk_xpyjlh = b.pyxx_bh and b.pyxx_ryid = a.jbqk_id " + 
				" inner join rsgl_yw_zwbd c on a.jbqk_xzwjlh = c.zwbd_bh and c.zwbd_ryid = a.jbqk_id " + 
				" inner join gzgl_cs_zwgz d on d.zwgz_zxny=201607 and d.zwgz_zwdj= c.zwbd_xzwjb "+
				" inner join gzgl_cs_zwgz e on e.zwgz_zxny=201607 and e.zwgz_zwdj= b.pyxx_xgwdj "+
				" inner join gg_xt_gljg f on a.jbqk_jcdwbm = f.gljg_bm "+
				" inner join gg_dm_zwjb g on c.zwbd_xzwjb = g.code "+
				" where (pyxx_sjtf=1 or substr(pyxx_xgwdj,1,2)='21') and d.zwgz_zwgz > e.zwgz_zwgz " + 
						" and a.jbqk_id not in (select pyxx_ryid from rsgl_yw_pyxx where Pyxx_baf = 0 or Pyxx_baf is null) ";
		if(gljg_lb == 4 || gljg_lb == 5) {//单位或二级单位查询
			fromstr += " and a.jbqk_dwbm = ? ";
			params.set(index.toString(), dwbm);
			index += 1;
			if(!jcdwbm.equals("0")) {
				fromstr += "and a.jbqk_jcdwbm = ? ";
				params.set(index.toString(), jcdwbm);
				index += 1;
			}
		}else if(gljg_lb == 6) {//部门查询
			fromstr += " and a.jbqk_jcdwbm = ? ";
			params.set(index.toString(), jcdwbm);
			index += 1;
		}else {
			return null;
		}
		
		if(!("").equals(name)) {	
			fromstr += " and a.jbqk_xm like ? ";
			params.set(index.toString(), "%"+name+"%");
			index += 1;
		}
		if(!("").equals(pzrq)) {
			fromstr += " and c.zwbd_pzrq > ? ";
			params.set(index.toString(), pzrq);
		}
		fromstr += " order by a.jbqk_jcdwbm, a.jbqk_id ";
		return this.paginate(page,MainConfig.pgRcdNum,sql,fromstr,params.getColumnValues());
	}
	/**
	 * 获取管理机构中职务晋升者信息列表
	 * @param dwbm 单位编码
	 * @param jcdwbm 部门编码
	 * @param gljg_lb 管理机构类别
	 * @return Page<RsglYwJbqkModel>
	 */
	public List<RsglYwJbqkModel> getZwjsUserList(String dwbm,String jcdwbm, int gljg_lb) {
		Record params = new Record();
		String index = "0";
		String sql = "select f.gljg_mc as jbqk_jcdwmc,a.jbqk_id, a.jbqk_xm, a.jbqk_zjbh,a.jbqk_xpyjlh ";
		String fromstr = "FROM rsgl_yw_jbqk a " + 
				" inner join rsgl_yw_pyxx b on a.Jbqk_xpyjlh = b.pyxx_bh and b.pyxx_ryid = a.jbqk_id " + 
				" inner join rsgl_yw_zwbd c on a.jbqk_xzwjlh = c.zwbd_bh and c.zwbd_ryid = a.jbqk_id " + 
				" inner join gzgl_cs_zwgz d on d.zwgz_zxny=201607 and d.zwgz_zwdj= c.zwbd_xzwjb "+
				" inner join gzgl_cs_zwgz e on e.zwgz_zxny=201607 and e.zwgz_zwdj= b.pyxx_xgwdj "+
				" inner join gg_xt_gljg f on a.jbqk_jcdwbm = f.gljg_bm "+
				" where (pyxx_sjtf=1 or substr(pyxx_xgwdj,1,2)='21') and d.zwgz_zwgz > e.zwgz_zwgz " + 
						" and a.jbqk_id not in (select pyxx_ryid from rsgl_yw_pyxx where Pyxx_baf = 0 or Pyxx_baf is null) ";
		if(gljg_lb == 4 || gljg_lb == 5) {//单位或二级单位查询
			fromstr += " and a.jbqk_dwbm = ? ";
			params.set(index, dwbm);
			index = "1";
			if(!jcdwbm.equals("0")) {
				fromstr += "and a.jbqk_jcdwbm = ? ";
				params.set(index, jcdwbm);
				index = "2";
			}
		}else if(gljg_lb == 6) {//部门查询
			fromstr += " and a.jbqk_jcdwbm = ? ";
			index = "1";
		}else {
			return null;
		}
		fromstr += " order by a.jbqk_jcdwbm, a.jbqk_id ";
		return this.find(sql+fromstr,params.getColumnValues());
	}

	/**
	 * 重新将人员的职务变动排序并赋值给zwbd_bh字段
	 * @param ryid 人员id
	 * @return true 成功，false 失败
	 */
	public boolean Resort(BigDecimal ryid) {
		final List<RsglYwZwbdModel> list = new RsglYwZwbdModel().getList(ryid);//按批准日期排序取出列表
		List<RsglYwZwbdModel> resortList = new ArrayList<RsglYwZwbdModel>(list.size());//初始化新的List
		for(int i=0;i<list.size();i++) {//将取出的列表的编号重新赋值并存入新的List中
			RsglYwZwbdModel ryz = new RsglYwZwbdModel();
			ryz.set("zwbd_ryid", list.get(i).get("zwbd_ryid"));
			ryz.set("zwbd_yzwjb", list.get(i).get("zwbd_yzwjb"));
			ryz.set("zwbd_yzwmc", list.get(i).get("zwbd_yzwmc"));
			ryz.set("zwbd_xzwjb", list.get(i).get("zwbd_xzwjb"));
			ryz.set("zwbd_xzwmc", list.get(i).get("zwbd_xzwmc"));
			ryz.set("zwbd_bz", list.get(i).get("zwbd_bz"));
			ryz.set("zwbd_gwid", list.get(i).get("zwbd_gwid"));
			ryz.set("zwbd_baf", list.get(i).get("zwbd_baf"));
			ryz.set("zwbd_bh", i+1);
			resortList.add(ryz);
		}
		final List<RsglYwZwbdModel> reList = resortList;
		final String sql = "delete from rsgl_yw_zwbd"
				+" where zwbd_ryid =? and zwbd_bh=?";
		boolean delete = Db.tx(new IAtom() {
					@Override
					public boolean run() throws SQLException {
						for(RsglYwZwbdModel ryz:list) {
							Db.update(sql,ryz.get("zwbd_id"),ryz.get("zwbd_bh"));
						}
						return true;
					}
			});
		if(delete) {
			return this.SaveList(reList);
		}else {
			return false;
		}
	}
	private boolean SaveList(final List<RsglYwZwbdModel> reList) {
		return Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				if(reList != null) {
					Db.batchSave(reList, 99999);
				}
				return true;
			}		
		});
	}

	/**
	 * 重新将人员的职务变动排序并赋值给zwbd_bh字段
	 * @param ryid 人员id
	 * @return 1 成功，0 失败
	 */
	public static int ResortZwbdList(BigDecimal ryid) {
		if(new RsglYwZwbdModel().Resort(ryid)) {
			return 1;
		}else {
			return 0;
		}
	}

	public int removeDuplicates(int[] nums) {
        int i = 1;
        int len = nums.length;
        int index = 0;
        while(i<len){
            int j = 0;
            if(nums[i-1] = nums[i] && j<2){
                j++;  
            }else if(nums[i-1] < nums[i]){
                j=0;
                
            }
        }
        return index+1;
	}
	public int findKthLargest(int[] nums, int k) {
        int low = 0;
        int high = nums.length-1;
        int x = high-k+1;
        quickSort(nums,low,high,x);
        return nums[x];
    }
    public void quickSort(int[] nums,int i,int j,int x){
        if(i == x) return;
        int low = i;
        int high = j;
        int key=nums[low];
        while(i<j){
            while(nums[i]<key && i<j){
                i++;
            }
            while(nums[j]>key && i<j){
                j--;
            }
            if(i<j){
                swap(nums,i,j);
            }
        }
        int temp = nums[i];
        nums[i] = key;
        nums[low] = temp;
        if(i>x) quickSort(nums,low,i-1,x);
        if(i<x) quickSort(nums,i+1,high,x);
    }
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
	}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){//递归
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}

RsglYwGwccModel model = GljgUserService.GetGwccInfo(gwcc_id);
String Path  = MainConfig.UploadBasePath+model.get("gwcc_tpdz");
File file=new File(Path);
		
if(rst == 1) {
	if(file.exists()&&file.isFile())
	        file.delete();
	}
		
String select = "select a.gljg_bm as id, b.gljg_mc||'--'||a.gljg_mc as name ";
String sqlExceptSelect = " from gg_xt_gljg a "+
		" left join gg_xt_gljg b on a.gljg_scbm = b.gljg_bm"+
		" where a.gljg_zxtm=? and a.gljg_mc like ? and a.gljg_scbm like ? ";
		{gwsz_dwbm:303600000045330001, gwsz_gl10:null, gwsz_gl3:0, gwsz_gl4:1, gwsz_gl5:1, gwsz_gl6:0, gwsz_gl7:0, gwsz_gl8:0, gwsz_gl9:null,
			 gwsz_gq1:null, gwsz_gq2:null, gwsz_gq3:null, gwsz_gq4:null, gwsz_gq5:null, gwsz_gq6:null,
			  gwsz_zj10:null, gwsz_zj11:null, gwsz_zj12:null, gwsz_zj13:null, gwsz_zj2:null, gwsz_zj3:null, gwsz_zj4:null, gwsz_zj5:null, gwsz_zj6:null, gwsz_zj7:null, gwsz_zj8:null, gwsz_zj9:null}

/**
* 查询部门中本岗位级别以及上级岗位是否有名额
* @param gwjb 岗位级别代码表（根据传入的String数组识别）
* @param dwbm 部门编码
* @param i 岗位等级：与实际级别对应，i+1等于实际级别
* @param rylb 人员类别：1 事业单位管理人员，2 事业单位专技人员，3 事业单位工人
* @return true 有，false 无
*/
public static boolean HasMe(String[] gwjb,RsglYwGwszdqModel ryg,int i,int rylb) {
	if(rylb != 0) {
		if((rylb == 1 && i == 2)||(rylb == 2 && i == 1)||(rylb == 3 && i == 0)) {//终止条件
			Map<String, Object> map = new GgDmModel().GetYprs(2, ryg.getStr("gwsz_dwbm"), gwjb[i]);
			Map<String, Object> map1 = new GgDmModel().GetNprs(2, ryg.getStr("gwsz_dwbm"), gwjb[i]);
			int result = Integer.parseInt(map.get("result").toString());
			int nprs = Integer.parseInt(map1.get("result").toString());
			int jhrs = 0;//计划人数——从数据库中查出的本部门本岗位等级计划的拟聘人数
			if(rylb == 1) {
				jhrs = Integer.parseInt(ryg.get("gwsz_gl"+(i+1)).toString());//事业单位管理人员计划的拟聘人数
			}else if(rylb == 2) {
				jhrs = Integer.parseInt(ryg.get("gwsz_zj"+(i+1)).toString());//事业单位专技人员计划的拟聘人数
			}else if(rylb == 3) {
				jhrs = Integer.parseInt(ryg.get("gwsz_gq"+(i+1)).toString());//事业单位工人计划的拟聘人数
			}
			if(jhrs > result+nprs) {
				// 该jhrs数据减一
				if(rylb == 1) {
					ryg.set("gwsz_gl"+(i+1),jhrs-1);//事业单位管理人员
				}else if(rylb == 2) {
					ryg.set("gwsz_zj"+(i+1),jhrs-1);//事业单位专技人员
				}else if(rylb == 3) {
					ryg.set("gwsz_gq"+(i+1),jhrs-1);//事业单位工人
				}
				return true;
			}else {
				return false;
			}
		}else {
			Map<String, Object> map = new GgDmModel().GetYprs(2, ryg.getStr("gwsz_dwbm"), gwjb[i]);
			Map<String, Object> map1 = new GgDmModel().GetNprs(2, ryg.getStr("gwsz_dwbm"), gwjb[i]);
			int result = Integer.parseInt(map.get("result").toString());
			int nprs = Integer.parseInt(map1.get("result").toString());
			int jhrs = 0;//计划人数——从数据库中查出的本部门本岗位等级计划的拟聘人数
			if(rylb == 1) {
				jhrs = Integer.parseInt(ryg.get("gwsz_gl"+(i+1)).toString());//事业单位管理人员计划的拟聘人数
			}else if(rylb == 2) {
				jhrs = Integer.parseInt(ryg.get("gwsz_zj"+(i+1)).toString());//事业单位专技人员计划的拟聘人数
			}else if(rylb == 3) {
				jhrs = Integer.parseInt(ryg.get("gwsz_gq"+(i+1)).toString());//事业单位工人计划的拟聘人数
			}
			if(jhrs > result+nprs) {
				// 该jhrs数据减一
				if(rylb == 1) {
					ryg.set("gwsz_gl"+(i+1),jhrs-1);//事业单位管理人员
				}else if(rylb == 2) {
					ryg.set("gwsz_zj"+(i+1),jhrs-1);//事业单位专技人员
				}else if(rylb == 3) {
					ryg.set("gwsz_gq"+(i+1),jhrs-1);//事业单位工人
				}
				return true;
			}else {
				return HasMe(gwjb,ryg,i-1,rylb);
			}
		}
	}
	return true;
}
