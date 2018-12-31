SELECT
	c.zcbd_ryid AS jbqk_id,
	c.zcbd_yzc,
	c.zcbd_yzcqdrq,
	c.zcbd_xzc,
	c.zcbd_xzcqdrq,
	c.zcbd_bz,
	c.zcbd_bh,
	A .jbqk_zjbh AS jbqk_zjbh,
	A .jbqk_xm AS jbqk_xm,
	A .JBQK_JCDWBM AS jbqk_jcdwbm,
	A .jbqk_gwlx AS jbqk_gwlx,
	f.gljg_mc AS jbqk_jcdwmc,
	g1. NAME AS yzc,
	G . NAME AS xzc
FROM
	rsgl_yw_jbqk A
left JOIN rsgl_yw_pyxx b ON A .Jbqk_xpyjlh = b.pyxx_bh
AND b.pyxx_ryid = A .jbqk_id
inner JOIN rsgl_yw_zcbd c ON A .jbqk_xzcjlh = c.zcbd_bh
AND c.zcbd_ryid = A .jbqk_id
INNER JOIN gzgl_cs_zwgz D ON d.zwgz_zxny = 201607
AND D .zwgz_zwdj = DECODE (
	SUBSTR (c.zcbd_xzc, 3, 1),
	'1',
	'2214',
	'2',
	'2223',
	'3',
	'2233',
	'4',
	'2242',
	'',
	'2251'
)
left JOIN gzgl_cs_zwgz E ON e.zwgz_zxny = 201607
AND D .zwgz_zwdj = DECODE (
	SUBSTR (b.pyxx_xgwdj, 3, 1),
	'1',
	'2214',
	'2',
	'2223',
	'3',
	'2233',
	'4',
	'2242',
	'',
	'2251'
)
INNER JOIN gg_xt_gljg f ON A .jbqk_jcdwbm = f.gljg_bm
INNER JOIN gg_dm_jszw G ON c.zcbd_xzc = G .code
INNER JOIN gg_dm_jszw g1 ON c.zcbd_yzc = g1.code
WHERE
	c.zcbd_baf = 1
AND (
	D .zwgz_zwgz > E .zwgz_zwgz
	OR b.pyxx_bh IS NULL
)
AND A .jbqk_id NOT IN (
	SELECT
		pyxx_ryid
	FROM
		rsgl_yw_pyxx
	WHERE
		Pyxx_baf = 0
)

c.zcbd_ryid as jbqk_id,
c.zcbd_yzc,
c.zcbd_yzcqdrq,
c.zcbd_xzc,
c.zcbd_xzcqdrq,
c.zcbd_bz,
c.zcbd_bh, 
a.jbqk_zjbh as jbqk_zjbh,
a.jbqk_xm as jbqk_xm, 
a.JBQK_JCDWBM AS jbqk_jcdwbm,
a.jbqk_gwlx as jbqk_gwlx,   
f.gljg_mc as jbqk_jcdwmc, 
g1.name as yzc,
g.name as xzc

调用以下函数求单位（部门）已经聘用数：
rsgz_func_yprs(bj number,dwbm  gg_xt_gljg.gljg_bm%type,gwdj  GG_DM_ZWJB.code%type )  return number as 
--功能：求指定单位/部门的某岗位已聘用数
--bj ：1求整个单位已聘用数；2求某个部门已聘用数；
--dwbm单位编码/部门编码，bj =1单位编码，bj =2部门编码；
--gwdj：岗位等级；
--返回：已聘用数；
事业单位管理人员：
2111	一级职员(正部)
2112	二级职员(副部)
2113	三级职员(正局)
2114	四级职员(副局)
2115	五级职员(正处)
2116	六级职员(副处)
2117	七级职员(正科)
2118	八级职员(副科)
2119	九级职员(科员)
2120	十级职员(办事员)
2199	管理见习期初期；
事业单位专技人员：
2201	一级（正高）
2212	二级（正高）
2213	三级（正高）
2214	四级（正高）
2221	五级（副高）
2222	六级（副高）
2223	七级（副高）
2231	八级（中级）
2232	九级（中级）
2233	十级（中级）
2241	十一级（初级）
2242	十二级（初级）
2251	十三级（员级）
2299	专技见习期初期
事业单位工人：
2311	一级(高级技师)
2312	二级(技师)
2313	三级(高级工)
2314	四级(中级工)
2315	五级(初级工)
2316	事业单位普通工