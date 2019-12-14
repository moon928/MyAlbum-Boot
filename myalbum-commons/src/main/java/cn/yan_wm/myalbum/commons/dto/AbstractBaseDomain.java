package cn.yan_wm.myalbum.commons.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 通用的领域模型
 */

@Data
public abstract class AbstractBaseDomain implements Serializable {
    private static final long serialVersionUID = 8796860946160892438L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
