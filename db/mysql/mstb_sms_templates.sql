﻿INSERT INTO test.mstb_sms_templates (SMS_TEMPLATES_ID,TEMPLATES_CODE,SMS_TEMPLATES_CONTENT,APPLICATION_ID,REMARK,STATE,RECORD_STATE,CREATED_USER_ID,CREATED_TIME,UPDATED_USER_ID,UPDATED_TIME) VALUES 
('101','111','1111','100007','1111',1,0,'admin',STR_TO_DATE('2015-04-19 16:15:20','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2015-04-19 16:15:36','%Y-%m-%d %H:%i:%s'))
,('61','eb_login','签入提醒：亲爱的用户，您预订的会议[$meetingTitle] 在$loginBegintime分钟后（$meetingStartTime）即将开始，现请办理签入手续，会议地点为[$meetingLocus][$meetingName]，谢谢!','21',NULL,1,1,'admin',STR_TO_DATE('2011-12-28 17:08:57','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-01-12 15:27:59','%Y-%m-%d %H:%i:%s'))
,('62','eb_request','亲爱的用户，您已成功预订[$meetingLocus][$meetingName]，会议主题为[$meetingTitle]，会议时间为[$meetingTime]，谢谢!','21',NULL,1,1,'admin',STR_TO_DATE('2011-12-28 17:09:44','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-01-06 22:55:55','%Y-%m-%d %H:%i:%s'))
,('63','eb_userCancel','亲爱的用户，会议[$meetingTitle]预订已取消，谢谢!','21',NULL,1,1,'admin',STR_TO_DATE('2011-12-28 17:10:15','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-01-06 23:03:05','%Y-%m-%d %H:%i:%s'))
,('81','eb_modify','亲爱的用户，会议[$oldMeetingTitle]已修改为[$meetingTitle]，[$meetingLocus][$meetingName]，[$meetingTime]，谢谢! ','21',NULL,1,1,'admin',STR_TO_DATE('2012-01-06 22:58:35','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-01-06 22:58:35','%Y-%m-%d %H:%i:%s'))
,('82','eb_sysCancel','亲爱的用户，您预订的会议[$meetingTitle]开始时间为$meetingStartTime，现已超出签入时间$meetingLastLoginTime，系统已自动取消该会议，谢谢!','21',NULL,1,1,'admin',STR_TO_DATE('2012-01-06 23:05:43','%Y-%m-%d %H:%i:%s'),'admin',STR_TO_DATE('2012-01-12 15:29:29','%Y-%m-%d %H:%i:%s'))
;