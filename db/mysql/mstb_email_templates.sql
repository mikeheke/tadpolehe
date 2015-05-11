INSERT INTO test.mstb_email_templates (EMAIL_TEMPLATES_ID,TEMPLATES_CODE,TEMPLATES_NAME,EMAIL_TEMPLATES_SUBJECT,EMAIL_TEMPLATES_CONTENT,APPLICATION_ID,ACCESSORY_FLAG,SEND_FLAG,REMARK,STATE,RECORD_STATE,CREATED_USER_ID,CREATED_TIME,UPDATED_USER_ID,UPDATED_TIME) VALUES 
('101','rf_create','申请单创建或导入时发送的邮件','银行转账支付退货款清单已经创建','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
店铺同事：<p>
 
　　银行转账支付退货款清单已经创建，请贵店铺于今天11点前完成《银行转账支付退货款清单》的提交工作。如当日无法通过系统提交清单，则先行以邮件方式回复相关情况至Angel Huang/FSD/China/Amway邮箱然后再补提交，以免耽误付款工作。多谢合作！ <p>

　　财务部联系人为：Zoe Zeng(Ext.5477) <p>
 
</body>

</html>','2',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-09-01 18:50:34','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2013-05-15 11:26:09','%Y-%m-%d %H:%i:%s'))
,('102','rf_informShops','通知未提交店铺','请完成尚未确认的《银行转账支付退货款清单》提交工作','<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
店铺同事：<p>
 
 　　贵店铺应于今天12点前完成尚未确认的《银行转账支付退货款清单》提交工作。为免耽误全国退货款的付款工作，若店铺未能按时提交清单，则财务部将暂缓支付该笔退货款，直至店铺完成提交工作后再行支付。特此知会！ <p>如果您已经完成清单的提交工作，请忽略此邮件，谢谢合作！<p>

　　财务部联系人为：Zoe Zeng(Ext.5477) <p>
 
</body>

</html>','2',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-09-01 18:55:37','%Y-%m-%d %H:%i:%s'),'CN087773',STR_TO_DATE('2014-07-24 21:40:25','%Y-%m-%d %H:%i:%s'))
,('121','mt_unSubmitted','会议门券未提交通知模板','您有会议门券销售明细表尚未提交','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事：<p>
 
　　贵店铺应在每月第五个工作日前提交《会议门券销售收入明细表》，至今尚未提交，请尽快填写并提交。如店铺当月无发生门券收入，则无需理会该邮件。多谢合作！ <p>

　　请点击此<a href="$FORM_CODE" target="_blank"> $CODE </a>查看详情。  <p>
 
财务—财务分析（销售）组 <p>
　　联络邮箱：AccountSalesTeam China/FSD/China/Amway
</body>

</html>','121',1,0,NULL,1,1,'admin',STR_TO_DATE('2011-09-07 11:11:10','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:34:00','%Y-%m-%d %H:%i:%s'))
,('141','ib_unSubmitted','店铺缴税专户余款','请提交《店铺缴税专户余款表》','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事： <p>
　　贵店铺应交$yearMonthStr《店铺缴税专户余款表》尚未提交，请尽快填写并提交。多谢合作！<p>
　　请点击此<a href="$FORM_CODE" target="_blank">链接</a>查看详情。<p>
　　广州财务部<p>
</body>

</html>','203',1,1,NULL,1,1,'admin',STR_TO_DATE('2011-09-12 20:27:07','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:38:13','%Y-%m-%d %H:%i:%s'))
,('161','at_infoShops','邮件通知未提交店铺','您有增值税发票抵扣联[$formCode]尚未提交','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事：<p> 
　　贵店铺应在每月最后一个工作日前提交《增值税发票抵扣联》，至今尚未提交，请尽快填写并提交。<p><p>
 
　　广州财务部税务组<p>
　　联络人：Sara Zhang 张贝/ Catherine Ling 凌碧君<p>
　　电话：  020-85198286/85198332     传真：  020-38912075<p>
</body>

</html>
','201',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-09-14 20:07:57','%Y-%m-%d %H:%i:%s'),'CN092809',STR_TO_DATE('2014-01-20 13:31:08','%Y-%m-%d %H:%i:%s'))
,('181','at_sendBack','财务部退回','增值税发票抵扣联申请单[$formCode]被退回','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事：<p>
　　你们店铺提交的增值税发票抵扣联申请单[$formCode]被退回。请尽快跟进，并将修改后的申请重新提交，以便财务部及时结算，谢谢你们的大力支持与合作！<p><p>
　　广州财务部税务组<p>
　　联络人：Sara Zhang 张贝/ Catherine Ling 凌碧君<p>
　　电话：  020-85198286/85198332     传真：  020-38912075<p>
</body>

</html>
','201',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-10-13 16:45:53','%Y-%m-%d %H:%i:%s'),'CN092809',STR_TO_DATE('2014-01-20 13:31:38','%Y-%m-%d %H:%i:%s'))
,('2','TE0002','反对法','发生大幅发送方的说法 密码：$PASSWORD','发送方的说法fdfdsfdsffff $USER_NAME','1',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-04-12 15:04:14','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-06-11 23:38:02','%Y-%m-%d %H:%i:%s'))
,('201','ft_unSubmitted','财务税务资料核对提交','请提交《财务税务资料核对》','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事： <p>
　　贵店铺应交$yearMonthStr《财务税务资料核对》尚未提交，请尽快填写并提交。多谢合作！<p>
　　请点击此<a href="$FORM_CODE" target="_blank">链接</a>查看详情。<p>

　　广州财务部<p>
</body>

</html>','261',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2011-11-06 22:32:50','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:40:57','%Y-%m-%d %H:%i:%s'))
,('221','eb_blacklist','列入黑名单','列入黑名单','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
您好：<p>
　　您一个月内迟到超过3次(含)或缺席超过3次(含)或早退超过3次(含)或迟到/缺席/早退/未签出合计超过4次(含)。在[$addBlacklistTime]您已列入黑名单，设置日期起[$blacklistDays]天内不允许使用会议预订功能。<p>
</body>

</html>','21',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2011-11-09 12:05:29','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:26:13','%Y-%m-%d %H:%i:%s'))
,('241','si_unSubmitted','请提交《店铺发票使用情况》','请提交 $MONTH 月 《店铺发票使用情况》','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事：<p> 
　　贵店铺应交 $MONTH 月 《店铺发票使用情况》尚未提交，请尽快填写并提交。多谢合作！ <p>
　　请点击此<a href="$FORM_CODE" target="_blank">链接</a>查看详情。 <p>
　　广州财务部税务组/税务部<p>
　　联络人：Lily Guo 郭嘉丽 / Candy Tang 唐金萍<p>
　　电话：  020-85198971/85194465    传真：  020-38772037<p>
 </body>

</html>','203',1,1,NULL,1,1,'admin',STR_TO_DATE('2011-11-11 19:09:31','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:54:31','%Y-%m-%d %H:%i:%s'))
;
INSERT INTO test.mstb_email_templates (EMAIL_TEMPLATES_ID,TEMPLATES_CODE,TEMPLATES_NAME,EMAIL_TEMPLATES_SUBJECT,EMAIL_TEMPLATES_CONTENT,APPLICATION_ID,ACCESSORY_FLAG,SEND_FLAG,REMARK,STATE,RECORD_STATE,CREATED_USER_ID,CREATED_TIME,UPDATED_USER_ID,UPDATED_TIME) VALUES 
('261','si_rollApply','卷式发票申请','您店铺卷式机打发票余量不足','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事：<p>
 
　　贵店铺卷式机打发票余量不足。请及时申请加印。若已办理加印申请，请忽略此邮件，多谢合作！ <p>

　　广州财务部税务组<p>
　　联络人：Lily Guo 郭嘉丽 / Candy Tang 唐金萍<p>
　　电话：  020-85198971/85194465    传真：  020-38772037<p>
 
</body>

</html>','241',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2011-11-14 15:36:16','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:39:28','%Y-%m-%d %H:%i:%s'))
,('281','smt_unSubmitted','smt_unSubmitted','请提交《每月报税情况汇总》','　　店铺同事：
　　贵店铺应交《每月报税情况汇总》尚未提交，请尽快填写并提交。多谢合作！ 
                                                                 
                                                                 广州财务部
','301',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2011-12-14 10:56:10','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-12-10 12:00:43','%Y-%m-%d %H:%i:%s'))
,('64','eb_request','会议邀请模板','会议邀请:[$meetingTitle]','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
会议室预订人[$bookingUser]邀请你参加以下会议：<p>
主题：[$meetingTitle]<p>
时间：[$meetingTime]<p>
地点：[$meetingLocus]<p>
所在会议室：[$meetingName]<p>
与会人员：[$meetingUsers]<p>
请点击此<a href="$meetingUrl" target="_blank">链接</a>查看详情。<p>
</body>

</html>','21',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-07-29 18:37:04','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:14:41','%Y-%m-%d %H:%i:%s'))
,('65','eb_cancel','会议取消模板','会议取消:[$meetingTitle]','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　各位与会人员，由于你未能及时在会议开始:$loginEndtime分钟后办理签入，系统已自动取消你对该会议室的预定，谢谢！<p>
主题:[$meetingTitle]<p>
时间:[$meetingTime]<p>
地点:[$meetingLocus]<p>
<br/>所在会议室:[$meetingName]<p>
与会人员:[$meetingUsers]<p>
请点击此<a href="$meetingUrl" target="_blank">链接</a>查看详情。 <p>
</body>

</html>','21',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-07-29 18:47:11','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:22:56','%Y-%m-%d %H:%i:%s'))
,('66','eb_login','会议签入提醒模板','会议室签入提醒','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　各位与会人员您预订的会议室$loginBegintime分钟后即将可以使用，现在已经可以办理签入，请您按时抵达会议室并签入登记；此签入功能将在会议开始$loginEndtime分钟后自动取消以您预定的会议开始时间计算）。<p>
　　如您未能及时办理签入，会议室管理员将有权取消您的预订。谢谢！<p>
温馨提醒：<p>
　　1．会议开始后5-30分钟内签入计”迟到“一次<p>
　　2．会议开始30分钟后签入或未签入计“缺席”一次<p>
主题:[$meetingTitle]<p>
时间:[$meetingTime]<p>
地点:[$meetingLocus]<p>
所在会议室:[$meetingName]<p>
与会人员:[$meetingUsers]<p>
请点击此<a href="$meetingUrl" target="_blank">链接</a>查看详情。<p>
</body>

</html>','21',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-07-29 19:06:58','%Y-%m-%d %H:%i:%s'),'CN012603',STR_TO_DATE('2012-06-20 11:25:19','%Y-%m-%d %H:%i:%s'))
,('82','cr_complete','申请单处理完成','支票质押返还申请[$formCode]已完成','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事:<p>
　　你们店铺提交的支票质押返还申请[$formCode]已于[$transferDate]交付银行处理。<p>

　　广州财务部销售组<p>
　　联络人：Zoe Zeng 曾斐媛 /Sharon Cao 曹红英<p>
　　电话：  020-85195477/85198816      传真：  020-38772037<p>
</body>

</html>','101',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-08-24 20:38:39','%Y-%m-%d %H:%i:%s'),'CN011178',STR_TO_DATE('2014-04-29 16:43:18','%Y-%m-%d %H:%i:%s'))
,('83','cr_sendBack','财务部退回','支票质押返还申请[$formCode]被退回','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
店铺同事：<p>
　　你们店铺提交的支票质押返还申请[$formCode]因[$sendBackReason]被退回，款项暂未能支付。请尽快跟进，并将修改后的申请重新提交，以便财务部及时支付款项，谢谢你们的大力支持与合作！<p>

　　广州财务部销售组<p>
　　联络人：Zoe Zeng 曾斐媛 /Sharon Cao 曹红英<p>
　　电话：  020-85195477/85198816      传真：  020-38772037<p>
</body>

</html>','101',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-08-24 20:41:19','%Y-%m-%d %H:%i:%s'),'CN011178',STR_TO_DATE('2014-04-29 16:41:29','%Y-%m-%d %H:%i:%s'))
,('84','cr_lackData','店铺缺少资料','支票质押返还申请需补齐材料','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事:<p>
　　你们店铺提交的支票质押返还申请[$formCode]因提供的资料不全，款项暂未能支付，请尽快补交（补充资料清单），以便财务部及时支付款项，谢谢你们的大力支持与合作！<p><p>
　　广州财务部销售组<p>
　　联络人：Zoe Zeng 曾斐媛 /Sharon Cao 曹红英<p>
　　电话：  020-85195477/85198816      传真：  020-38772037<p>
</body>

</html>','101',0,1,NULL,1,1,'admin',STR_TO_DATE('2011-08-24 20:43:29','%Y-%m-%d %H:%i:%s'),'CN011178',STR_TO_DATE('2014-04-29 16:43:58','%Y-%m-%d %H:%i:%s'))
,('85','cr_informShops','邮件通知未提交店铺','支票购货之质押现金返还申请未提交','店铺同事:
你们店铺本月未提交支票购货之质押现金返还申请，请尽快提交申请，以便财务部及时支付款项，谢谢你们的大力支持与合作！
广州财务部销售组
   联络人：Demi Zheng 郑晓琨 / Cynthia Kong 孔少婵
   电话：  020-85198461/85198877      传真：  020-38772037
','101',0,1,NULL,1,0,'admin',STR_TO_DATE('2011-08-24 22:07:30','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2011-08-24 22:07:30','%Y-%m-%d %H:%i:%s'))
,('88301','fm_not_followup','假货通报跟进','XXXX','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　您好，您发来的贵地区假货情况已获悉，但因$reason_content的原因，该投诉已无法跟进，谢谢您的关注，如有任何新情况出现，请与Beth Hu（Tel: 021-23056582）、Hope He（Tel: 021-23056569）联系。
 </body>

</html>','88200545',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2013-12-19 16:43:12','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2013-12-19 16:43:12','%Y-%m-%d %H:%i:%s'))
;
INSERT INTO test.mstb_email_templates (EMAIL_TEMPLATES_ID,TEMPLATES_CODE,TEMPLATES_NAME,EMAIL_TEMPLATES_SUBJECT,EMAIL_TEMPLATES_CONTENT,APPLICATION_ID,ACCESSORY_FLAG,SEND_FLAG,REMARK,STATE,RECORD_STATE,CREATED_USER_ID,CREATED_TIME,UPDATED_USER_ID,UPDATED_TIME) VALUES 
('88341','fm_submit_sendEmail','表单提交时群发给处理人','$ENGLISH_NAME提交了一张[$APPLICATION_NAME]申请[$FORM_CODE]，请知悉','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　请点击文档链接以查看申请：链接 <a href="$FORM_URL">$FORM_CODE</a> </body>
</html>','88200545',1,1,NULL,1,1,'admin',STR_TO_DATE('2013-12-27 15:32:39','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2013-12-27 18:50:48','%Y-%m-%d %H:%i:%s'))
,('88361','fm_close_sendEmail','结案时发送邮件','假货通报表$FORM_CODE已结案。','      假货通报表$FORM_CODE已结案。','88200545',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2014-01-07 12:28:41','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2014-01-07 12:49:10','%Y-%m-%d %H:%i:%s'))
,('88362','fm_notfollowup_sendEmail','无法跟进时发送邮件','假货通报表($FORM_CODE)无法跟进。','您好，您发来的贵地区假货情况已收悉，但因（******）的原因，该投诉已无法跟进，谢谢您的关注，如有任何新情况出现，请与Lucy Li（Ext：8854）联系。
','88200545',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2014-01-07 12:46:49','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2014-01-07 12:46:49','%Y-%m-%d %H:%i:%s'))
,('99301','cda_unSubmitted','未提交日记账店铺','提交日记账','<html>

<head>
<META http-equiv=Content-Type content="text/html; charset=GBK">
</head>

<body>
　　店铺同事：<p>

　请提交上个月($MONTH月$SHOPNAME($SHOPCODE)店)的现金日记账 <p>


</body>

</html>','99200522',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2014-02-21 17:55:53','%Y-%m-%d %H:%i:%s'),'CN092809',STR_TO_DATE('2014-10-08 11:18:41','%Y-%m-%d %H:%i:%s'))
,('99321','cda_applyChange','申请修改','申请修改','<html>

<head>
<META http-equiv=Content-Type content="text/html; 
charset=GBK">
</head>

<body>
　  您好，有一张单申请修改<p>
　  申请修改原因：$CODE<p><p>

　　<p>
   </body>

</html>
','99200522',NULL,1,NULL,1,1,'admin',STR_TO_DATE('2014-02-26 13:31:39','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2014-02-26 17:27:16','%Y-%m-%d %H:%i:%s'))
;