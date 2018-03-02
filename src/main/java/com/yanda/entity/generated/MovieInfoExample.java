package com.yanda.entity.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieInfoExample() {
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

        public Criteria andMvTitleIsNull() {
            addCriterion("mv_title is null");
            return (Criteria) this;
        }

        public Criteria andMvTitleIsNotNull() {
            addCriterion("mv_title is not null");
            return (Criteria) this;
        }

        public Criteria andMvTitleEqualTo(String value) {
            addCriterion("mv_title =", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleNotEqualTo(String value) {
            addCriterion("mv_title <>", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleGreaterThan(String value) {
            addCriterion("mv_title >", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleGreaterThanOrEqualTo(String value) {
            addCriterion("mv_title >=", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleLessThan(String value) {
            addCriterion("mv_title <", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleLessThanOrEqualTo(String value) {
            addCriterion("mv_title <=", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleLike(String value) {
            addCriterion("mv_title like", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleNotLike(String value) {
            addCriterion("mv_title not like", value, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleIn(List<String> values) {
            addCriterion("mv_title in", values, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleNotIn(List<String> values) {
            addCriterion("mv_title not in", values, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleBetween(String value1, String value2) {
            addCriterion("mv_title between", value1, value2, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvTitleNotBetween(String value1, String value2) {
            addCriterion("mv_title not between", value1, value2, "mvTitle");
            return (Criteria) this;
        }

        public Criteria andMvDescIsNull() {
            addCriterion("mv_desc is null");
            return (Criteria) this;
        }

        public Criteria andMvDescIsNotNull() {
            addCriterion("mv_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMvDescEqualTo(String value) {
            addCriterion("mv_desc =", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescNotEqualTo(String value) {
            addCriterion("mv_desc <>", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescGreaterThan(String value) {
            addCriterion("mv_desc >", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescGreaterThanOrEqualTo(String value) {
            addCriterion("mv_desc >=", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescLessThan(String value) {
            addCriterion("mv_desc <", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescLessThanOrEqualTo(String value) {
            addCriterion("mv_desc <=", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescLike(String value) {
            addCriterion("mv_desc like", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescNotLike(String value) {
            addCriterion("mv_desc not like", value, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescIn(List<String> values) {
            addCriterion("mv_desc in", values, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescNotIn(List<String> values) {
            addCriterion("mv_desc not in", values, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescBetween(String value1, String value2) {
            addCriterion("mv_desc between", value1, value2, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDescNotBetween(String value1, String value2) {
            addCriterion("mv_desc not between", value1, value2, "mvDesc");
            return (Criteria) this;
        }

        public Criteria andMvDurationIsNull() {
            addCriterion("mv_duration is null");
            return (Criteria) this;
        }

        public Criteria andMvDurationIsNotNull() {
            addCriterion("mv_duration is not null");
            return (Criteria) this;
        }

        public Criteria andMvDurationEqualTo(String value) {
            addCriterion("mv_duration =", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationNotEqualTo(String value) {
            addCriterion("mv_duration <>", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationGreaterThan(String value) {
            addCriterion("mv_duration >", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationGreaterThanOrEqualTo(String value) {
            addCriterion("mv_duration >=", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationLessThan(String value) {
            addCriterion("mv_duration <", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationLessThanOrEqualTo(String value) {
            addCriterion("mv_duration <=", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationLike(String value) {
            addCriterion("mv_duration like", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationNotLike(String value) {
            addCriterion("mv_duration not like", value, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationIn(List<String> values) {
            addCriterion("mv_duration in", values, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationNotIn(List<String> values) {
            addCriterion("mv_duration not in", values, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationBetween(String value1, String value2) {
            addCriterion("mv_duration between", value1, value2, "mvDuration");
            return (Criteria) this;
        }

        public Criteria andMvDurationNotBetween(String value1, String value2) {
            addCriterion("mv_duration not between", value1, value2, "mvDuration");
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

        public Criteria andClassifyIdIsNull() {
            addCriterion("classify_id is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNotNull() {
            addCriterion("classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdEqualTo(Integer value) {
            addCriterion("classify_id =", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotEqualTo(Integer value) {
            addCriterion("classify_id <>", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThan(Integer value) {
            addCriterion("classify_id >", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classify_id >=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThan(Integer value) {
            addCriterion("classify_id <", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("classify_id <=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIn(List<Integer> values) {
            addCriterion("classify_id in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotIn(List<Integer> values) {
            addCriterion("classify_id not in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("classify_id between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classify_id not between", value1, value2, "classifyId");
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