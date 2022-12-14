/*
 * Copyright 2022 changgg.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.cuukenn.easyadmin.module.system.dao;

import io.github.cuukenn.easyadmin.module.system.dao.support.MenuTypeConverter;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyframework.core.dao.IBoolLogicDelete;
import io.github.cuukenn.easyframework.jpa.dao.AbstractJpaEntityBool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author changgg
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "sys_menu")
@Table(appliesTo = "sys_menu", comment = "่ๅ่กจ")
@DynamicUpdate
@DynamicInsert
@Where(clause = IBoolLogicDelete.LOGIC_DELETE_SQL)
public class MenuPo extends AbstractJpaEntityBool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("id")
	private Long id;
	@Comment("็ถ่็นid")
	@Column(nullable = false)
	private Long parentId;
	@Comment("่ๅๅ็งฐ")
	@Column(length = 20, nullable = false)
	private String name;
	@Comment("่ๅๅพๆ?")
	@Column(length = 50)
	private String icon;
	@Comment("่ๅ้กบๅบ")
	@Column(nullable = false)
	private Integer number;
	@Comment("่ทฏ็ฑ่ทฏๅพ")
	@Column(length = 50)
	private String routerPath;
	@Comment("็ปไปถ่ทฏๅพ")
	@Column(length = 50)
	private String componentPath;
	@Comment("่ๅๆ้")
	@Column(length = 50)
	private String permission;
	@Comment("่ๅ็ฑปๅ")
	@Column(nullable = false)
	@Convert(converter = MenuTypeConverter.class)
	private MenuType type;
	@Comment("่ๅ็ถๆ(ๅผๅฏ/ๅณ้ญ)")
	@ColumnDefault(value = "false")
	private Boolean status;
	@SuppressWarnings("JpaDataSourceORMInspection")
	@ManyToMany
	@JoinTable(name = "sys_role_menu",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "menu_id"))
	@ToString.Exclude
	private Set<UserPo> users = new LinkedHashSet<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
