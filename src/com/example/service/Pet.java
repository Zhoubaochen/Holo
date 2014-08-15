package com.example.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Pet类 anroid要求调用远程Service的参数和返回值都必须实现Parcelable接口
 * 
 * @author Samuel
 * 
 */
public class Pet implements Parcelable {

	private String color;
	private double weight;

	public Pet() {
	}

	public Pet(String name, double weight) {
		super();

		this.color = name;
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Pet other = (Pet) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
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

		dest.writeString(color);
		dest.writeDouble(weight);

	}

	// 添加一个静态成员，bixu为CREATOR，该对象实现了Parcelable.Createor接口
	public static final Parcelable.Creator<Pet> CREATOR = new Creator<Pet>() {

		@Override
		public Pet[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Pet[size];
		}

		@Override
		public Pet createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Pet(source.readString(), source.readDouble());
		}
	};

}
