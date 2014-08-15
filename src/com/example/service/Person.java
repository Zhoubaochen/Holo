package com.example.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Person类
 * anroid要求调用远程Service的参数和返回值都必须实现Parcelable接口
 * 还必须有相应的aidl文件声明才可以在aidl中使用
 * @author Samuel
 * 
 */
public class Person implements Parcelable {
	private Integer id;
	private String name;
	private String password;

	public Person() {
	}

	public Person(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// 将对象数据写到parcel
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(password);

	}

	// 添加一个静态成员，bixu为CREATOR，该对象实现了Parcelable.Createor接口
	public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {

		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}

		@Override
		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Person(source.readInt(), source.readString(),
					source.readString());
		}
	};

}
