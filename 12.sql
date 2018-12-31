


SELECT
	A .gljg_bm AS ID,
	b.gljg_mc ||'--'|| A .gljg_mc AS NAME ,a.gljg_scbm,a.GLJG_LB
FROM
	gg_xt_gljg A
LEFT JOIN gg_xt_gljg b on A .gljg_scbm = b.gljg_bm
WHERE
	A .gljg_zxtm = 30
AND A .gljg_mc LIKE '%九江%'
AND A .gljg_scbm LIKE '30360000%'



WHERE  jbqk_id not in (select pyxx_ryid from rsgl_yw_pyxx where pyxx_gwid = 0)  and to_char(JBQK_DWBM) like '30360000%'  and jbqk_xm like '%%'  order by jbqk_jcdwbm

if(bh == 0) {
			int zdbh = new RsglYwPyxxModel().GetNextXh(ryid).intValue()-1;
}

BigDecimal lb = org.get("gljg_lb");

//1.求单位/部门已经聘用人数的计算函数
create or replace function rsgz_func_yprs(bj number,dwbm  gg_xt_gljg.gljg_bm%type,gwdj  GG_DM_ZWJB.code%type )  return number as 
--功能：求指定单位/部门的某岗位已聘用数
--bj ：1求整个单位该岗位已聘用数；2求某个部门该岗位已聘用数；
--bj ：3求整个单位高于等于该岗位已聘用数；2求某个部门高于等于该岗位已聘用数；
--dwbm单位编码/部门编码，bj =1、3单位编码，bj =2、4部门编码；
--gwdj：岗位等级；
--返回：已聘用数；

//2.求单位/部门已经拟聘用人数的计算函数
create or replace function rsgz_func_nprs(bj number,dwbm  gg_xt_gljg.gljg_bm%type,gwdj  GG_DM_ZWJB.code%type )  return number as 
--功能：求指定单位/部门的某岗位拟聘用数
--bj ：1求整个单位该岗位拟聘用数；2求某个部门该岗位拟聘用数；
--bj ：3求整个单位高于等于该岗位拟聘用数；4求某个部门高于等于该岗位拟聘用数；
--dwbm单位编码/部门编码，bj =1、3单位编码，bj =2、4部门编码；
--gwdj：岗位等级；
--返回：拟聘用数；

--手术查询
select a.medical_depart,b.surgery_id,c.name as surgery_name,count(b.surgery_id)
from wm_consult a
inner join wm_consult_curcure_surgery b on a.id = b.consult_id
inner join wm_surgery c on b.surgery_id = c.id
--这里添加条件，暂时考虑是:
where a.medical_depart = ''(科室) and a.out_time = 0(住院患者) --或者 a.out_time >= '' and a.out_time <=''(出院患者) 
group by a.medical_depart,b.surgery_id

--手术完成医生查询 
select a.id,a.name,ifnull(b.dct1_num,0),ifnull(c.dct2_num,0),ifnull(d.dct3_num,0)
from wm_sys_user a 
left join (select a.id,a.name,count(b.id) as dct1_num
from wm_sys_user a
left join wm_consult_curcure_surgery b on b.dct1 = a.id
left join wm_consult c on c.id = b.consult_id
where b.surgery_id = ? and c.out_time >= ? and c.out_time<= ?--这里是手术id和出院条件查询条件
group by a.id) b on a.id = b.id
left join (select a.id,a.name,count(b.id) as dct2_num
from wm_sys_user a
left join wm_consult_curcure_surgery b on b.dct2 = a.id
left join wm_consult c on c.id = b.consult_id
where b.surgery_id = ? and c.out_time >= ? and c.out_time<= ?--这里是手术id和出院条件查询条件
group by a.id) c on a.id = c.id
left join (select a.id,a.name,count(b.id) as dct3_num
from wm_sys_user a
left join wm_consult_curcure_surgery b on b.dct3 = a.id
left join wm_consult c on c.id = b.consult_id
where b.surgery_id = ? and c.out_time >= ? and c.out_time<= ?--这里是手术id和出院条件查询条件
group by a.id) d on a.id = d.id
where a.medical_depart = ? --这里是医生所在科室查询条件

--住院患者查询
--初步想法是连接wm_consult和医生诊断表之后的表通过疾病id和医生id两列分组之后将医生id分组产生的值多条数据合并为一条