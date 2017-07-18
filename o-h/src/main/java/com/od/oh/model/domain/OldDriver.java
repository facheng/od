package com.od.oh.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by wangfacheng on 2017-07-18.
 */

@Entity
@Table(name = "old_driver_record")
@SequenceGenerator(name = "seq_old_driver_record", sequenceName = "seq_old_driver_record")
public class OldDriver {

    @Id
    @GeneratedValue(generator = "seq_old_driver_record", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    public OldDriver() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
