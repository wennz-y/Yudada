package com.yupi.yudada.model.vo;

import cn.hutool.json.JSONUtil;
import com.yupi.yudada.model.dto.question.QuestionContentDTO;
import com.yupi.yudada.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目视图
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://www.code-nav.cn">编程导航学习圈</a>
 */
@Data
public class QuestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目内容（json格式）,转换为封装类(DTO数据传输对象)避免解析json时出现异常
     */
    private QuestionContentDTO questionContent;

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建用户信息
     */
    private UserVO User;

    /**
     * vo封装类转dto转对象
     *
     * @param questionVO
     * @return
     */
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);     //将vo封装类中的属性值复制到对象question中
        QuestionContentDTO questionContentDTO= questionVO.getQuestionContent();     //获取封装类中的questionContentDTO对象
        question.setQuestionContent(JSONUtil.toJsonStr(questionContentDTO));
        return question;
    }

    /**
     * 对象转dto转vo封装类
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        questionVO.setQuestionContent(JSONUtil.toBean(question.getQuestionContent(), QuestionContentDTO.class));
        return questionVO;
    }
}
