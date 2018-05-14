package com.yanda.entity.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EpisodeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EpisodeInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andEpisodeIdIsNull() {
            addCriterion("episode_id is null");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdIsNotNull() {
            addCriterion("episode_id is not null");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdEqualTo(Long value) {
            addCriterion("episode_id =", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotEqualTo(Long value) {
            addCriterion("episode_id <>", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdGreaterThan(Long value) {
            addCriterion("episode_id >", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("episode_id >=", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdLessThan(Long value) {
            addCriterion("episode_id <", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdLessThanOrEqualTo(Long value) {
            addCriterion("episode_id <=", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdIn(List<Long> values) {
            addCriterion("episode_id in", values, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotIn(List<Long> values) {
            addCriterion("episode_id not in", values, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdBetween(Long value1, Long value2) {
            addCriterion("episode_id between", value1, value2, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotBetween(Long value1, Long value2) {
            addCriterion("episode_id not between", value1, value2, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameIsNull() {
            addCriterion("episode_name is null");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameIsNotNull() {
            addCriterion("episode_name is not null");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameEqualTo(String value) {
            addCriterion("episode_name =", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotEqualTo(String value) {
            addCriterion("episode_name <>", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameGreaterThan(String value) {
            addCriterion("episode_name >", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("episode_name >=", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameLessThan(String value) {
            addCriterion("episode_name <", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameLessThanOrEqualTo(String value) {
            addCriterion("episode_name <=", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameLike(String value) {
            addCriterion("episode_name like", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotLike(String value) {
            addCriterion("episode_name not like", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameIn(List<String> values) {
            addCriterion("episode_name in", values, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotIn(List<String> values) {
            addCriterion("episode_name not in", values, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameBetween(String value1, String value2) {
            addCriterion("episode_name between", value1, value2, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotBetween(String value1, String value2) {
            addCriterion("episode_name not between", value1, value2, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroIsNull() {
            addCriterion("episode_intro is null");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroIsNotNull() {
            addCriterion("episode_intro is not null");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroEqualTo(String value) {
            addCriterion("episode_intro =", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroNotEqualTo(String value) {
            addCriterion("episode_intro <>", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroGreaterThan(String value) {
            addCriterion("episode_intro >", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroGreaterThanOrEqualTo(String value) {
            addCriterion("episode_intro >=", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroLessThan(String value) {
            addCriterion("episode_intro <", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroLessThanOrEqualTo(String value) {
            addCriterion("episode_intro <=", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroLike(String value) {
            addCriterion("episode_intro like", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroNotLike(String value) {
            addCriterion("episode_intro not like", value, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroIn(List<String> values) {
            addCriterion("episode_intro in", values, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroNotIn(List<String> values) {
            addCriterion("episode_intro not in", values, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroBetween(String value1, String value2) {
            addCriterion("episode_intro between", value1, value2, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeIntroNotBetween(String value1, String value2) {
            addCriterion("episode_intro not between", value1, value2, "episodeIntro");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumIsNull() {
            addCriterion("episode_num is null");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumIsNotNull() {
            addCriterion("episode_num is not null");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumEqualTo(Integer value) {
            addCriterion("episode_num =", value, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumNotEqualTo(Integer value) {
            addCriterion("episode_num <>", value, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumGreaterThan(Integer value) {
            addCriterion("episode_num >", value, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("episode_num >=", value, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumLessThan(Integer value) {
            addCriterion("episode_num <", value, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumLessThanOrEqualTo(Integer value) {
            addCriterion("episode_num <=", value, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumIn(List<Integer> values) {
            addCriterion("episode_num in", values, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumNotIn(List<Integer> values) {
            addCriterion("episode_num not in", values, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumBetween(Integer value1, Integer value2) {
            addCriterion("episode_num between", value1, value2, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andEpisodeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("episode_num not between", value1, value2, "episodeNum");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdIsNull() {
            addCriterion("img_appendix_id is null");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdIsNotNull() {
            addCriterion("img_appendix_id is not null");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdEqualTo(Long value) {
            addCriterion("img_appendix_id =", value, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdNotEqualTo(Long value) {
            addCriterion("img_appendix_id <>", value, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdGreaterThan(Long value) {
            addCriterion("img_appendix_id >", value, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdGreaterThanOrEqualTo(Long value) {
            addCriterion("img_appendix_id >=", value, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdLessThan(Long value) {
            addCriterion("img_appendix_id <", value, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdLessThanOrEqualTo(Long value) {
            addCriterion("img_appendix_id <=", value, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdIn(List<Long> values) {
            addCriterion("img_appendix_id in", values, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdNotIn(List<Long> values) {
            addCriterion("img_appendix_id not in", values, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdBetween(Long value1, Long value2) {
            addCriterion("img_appendix_id between", value1, value2, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andImgAppendixIdNotBetween(Long value1, Long value2) {
            addCriterion("img_appendix_id not between", value1, value2, "imgAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdIsNull() {
            addCriterion("mv_appendix_id is null");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdIsNotNull() {
            addCriterion("mv_appendix_id is not null");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdEqualTo(Long value) {
            addCriterion("mv_appendix_id =", value, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdNotEqualTo(Long value) {
            addCriterion("mv_appendix_id <>", value, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdGreaterThan(Long value) {
            addCriterion("mv_appendix_id >", value, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdGreaterThanOrEqualTo(Long value) {
            addCriterion("mv_appendix_id >=", value, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdLessThan(Long value) {
            addCriterion("mv_appendix_id <", value, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdLessThanOrEqualTo(Long value) {
            addCriterion("mv_appendix_id <=", value, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdIn(List<Long> values) {
            addCriterion("mv_appendix_id in", values, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdNotIn(List<Long> values) {
            addCriterion("mv_appendix_id not in", values, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdBetween(Long value1, Long value2) {
            addCriterion("mv_appendix_id between", value1, value2, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvAppendixIdNotBetween(Long value1, Long value2) {
            addCriterion("mv_appendix_id not between", value1, value2, "mvAppendixId");
            return (Criteria) this;
        }

        public Criteria andMvIdIsNull() {
            addCriterion("mv_id is null");
            return (Criteria) this;
        }

        public Criteria andMvIdIsNotNull() {
            addCriterion("mv_id is not null");
            return (Criteria) this;
        }

        public Criteria andMvIdEqualTo(Long value) {
            addCriterion("mv_id =", value, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdNotEqualTo(Long value) {
            addCriterion("mv_id <>", value, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdGreaterThan(Long value) {
            addCriterion("mv_id >", value, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdGreaterThanOrEqualTo(Long value) {
            addCriterion("mv_id >=", value, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdLessThan(Long value) {
            addCriterion("mv_id <", value, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdLessThanOrEqualTo(Long value) {
            addCriterion("mv_id <=", value, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdIn(List<Long> values) {
            addCriterion("mv_id in", values, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdNotIn(List<Long> values) {
            addCriterion("mv_id not in", values, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdBetween(Long value1, Long value2) {
            addCriterion("mv_id between", value1, value2, "mvId");
            return (Criteria) this;
        }

        public Criteria andMvIdNotBetween(Long value1, Long value2) {
            addCriterion("mv_id not between", value1, value2, "mvId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andVipTypeIsNull() {
            addCriterion("vip_type is null");
            return (Criteria) this;
        }

        public Criteria andVipTypeIsNotNull() {
            addCriterion("vip_type is not null");
            return (Criteria) this;
        }

        public Criteria andVipTypeEqualTo(Integer value) {
            addCriterion("vip_type =", value, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeNotEqualTo(Integer value) {
            addCriterion("vip_type <>", value, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeGreaterThan(Integer value) {
            addCriterion("vip_type >", value, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("vip_type >=", value, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeLessThan(Integer value) {
            addCriterion("vip_type <", value, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeLessThanOrEqualTo(Integer value) {
            addCriterion("vip_type <=", value, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeIn(List<Integer> values) {
            addCriterion("vip_type in", values, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeNotIn(List<Integer> values) {
            addCriterion("vip_type not in", values, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeBetween(Integer value1, Integer value2) {
            addCriterion("vip_type between", value1, value2, "vipType");
            return (Criteria) this;
        }

        public Criteria andVipTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("vip_type not between", value1, value2, "vipType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}