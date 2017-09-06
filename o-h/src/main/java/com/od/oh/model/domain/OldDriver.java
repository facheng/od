package com.od.oh.model.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Table(name = "old_driver")
@SequenceGenerator(name = "seq_old_driver", sequenceName = "seq_old_driver")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OldDriver {

    @Id
    @GeneratedValue(generator = "seq_old_driver", strategy = GenerationType.SEQUENCE)
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

    @Override
    public String toString() {
        return "OldDriver{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}
