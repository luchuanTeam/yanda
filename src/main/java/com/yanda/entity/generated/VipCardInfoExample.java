package com.yanda.entity.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VipCardInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VipCardInfoExample() {
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

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(Integer value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(Integer value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(Integer value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(Integer value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(Integer value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<Integer> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<Integer> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(Integer value1, Integer value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardNumIsNull() {
            addCriterion("card_num is null");
            return (Criteria) this;
        }

        public Criteria andCardNumIsNotNull() {
            addCriterion("card_num is not null");
            return (Criteria) this;
        }

        public Criteria andCardNumEqualTo(String value) {
            addCriterion("card_num =", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotEqualTo(String value) {
            addCriterion("card_num <>", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumGreaterThan(String value) {
            addCriterion("card_num >", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumGreaterThanOrEqualTo(String value) {
            addCriterion("card_num >=", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumLessThan(String value) {
            addCriterion("card_num <", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumLessThanOrEqualTo(String value) {
            addCriterion("card_num <=", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumLike(String value) {
            addCriterion("card_num like", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotLike(String value) {
            addCriterion("card_num not like", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumIn(List<String> values) {
            addCriterion("card_num in", values, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotIn(List<String> values) {
            addCriterion("card_num not in", values, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumBetween(String value1, String value2) {
            addCriterion("card_num between", value1, value2, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotBetween(String value1, String value2) {
            addCriterion("card_num not between", value1, value2, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardPasswordIsNull() {
            addCriterion("card_password is null");
            return (Criteria) this;
        }

        public Criteria andCardPasswordIsNotNull() {
            addCriterion("card_password is not null");
            return (Criteria) this;
        }

        public Criteria andCardPasswordEqualTo(String value) {
            addCriterion("card_password =", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordNotEqualTo(String value) {
            addCriterion("card_password <>", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordGreaterThan(String value) {
            addCriterion("card_password >", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("card_password >=", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordLessThan(String value) {
            addCriterion("card_password <", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordLessThanOrEqualTo(String value) {
            addCriterion("card_password <=", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordLike(String value) {
            addCriterion("card_password like", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordNotLike(String value) {
            addCriterion("card_password not like", value, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordIn(List<String> values) {
            addCriterion("card_password in", values, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordNotIn(List<String> values) {
            addCriterion("card_password not in", values, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordBetween(String value1, String value2) {
            addCriterion("card_password between", value1, value2, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andCardPasswordNotBetween(String value1, String value2) {
            addCriterion("card_password not between", value1, value2, "cardPassword");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysIsNull() {
            addCriterion("purchase_days is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysIsNotNull() {
            addCriterion("purchase_days is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysEqualTo(Integer value) {
            addCriterion("purchase_days =", value, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysNotEqualTo(Integer value) {
            addCriterion("purchase_days <>", value, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysGreaterThan(Integer value) {
            addCriterion("purchase_days >", value, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_days >=", value, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysLessThan(Integer value) {
            addCriterion("purchase_days <", value, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_days <=", value, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysIn(List<Integer> values) {
            addCriterion("purchase_days in", values, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysNotIn(List<Integer> values) {
            addCriterion("purchase_days not in", values, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysBetween(Integer value1, Integer value2) {
            addCriterion("purchase_days between", value1, value2, "purchaseDays");
            return (Criteria) this;
        }

        public Criteria andPurchaseDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_days not between", value1, value2, "purchaseDays");
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

        public Criteria andExpTimeIsNull() {
            addCriterion("exp_time is null");
            return (Criteria) this;
        }

        public Criteria andExpTimeIsNotNull() {
            addCriterion("exp_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpTimeEqualTo(Date value) {
            addCriterion("exp_time =", value, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeNotEqualTo(Date value) {
            addCriterion("exp_time <>", value, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeGreaterThan(Date value) {
            addCriterion("exp_time >", value, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exp_time >=", value, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeLessThan(Date value) {
            addCriterion("exp_time <", value, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeLessThanOrEqualTo(Date value) {
            addCriterion("exp_time <=", value, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeIn(List<Date> values) {
            addCriterion("exp_time in", values, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeNotIn(List<Date> values) {
            addCriterion("exp_time not in", values, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeBetween(Date value1, Date value2) {
            addCriterion("exp_time between", value1, value2, "expTime");
            return (Criteria) this;
        }

        public Criteria andExpTimeNotBetween(Date value1, Date value2) {
            addCriterion("exp_time not between", value1, value2, "expTime");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andIsForeverIsNull() {
            addCriterion("is_forever is null");
            return (Criteria) this;
        }

        public Criteria andIsForeverIsNotNull() {
            addCriterion("is_forever is not null");
            return (Criteria) this;
        }

        public Criteria andIsForeverEqualTo(Boolean value) {
            addCriterion("is_forever =", value, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverNotEqualTo(Boolean value) {
            addCriterion("is_forever <>", value, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverGreaterThan(Boolean value) {
            addCriterion("is_forever >", value, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_forever >=", value, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverLessThan(Boolean value) {
            addCriterion("is_forever <", value, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverLessThanOrEqualTo(Boolean value) {
            addCriterion("is_forever <=", value, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverIn(List<Boolean> values) {
            addCriterion("is_forever in", values, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverNotIn(List<Boolean> values) {
            addCriterion("is_forever not in", values, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverBetween(Boolean value1, Boolean value2) {
            addCriterion("is_forever between", value1, value2, "isForever");
            return (Criteria) this;
        }

        public Criteria andIsForeverNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_forever not between", value1, value2, "isForever");
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