package com.example.service;
import com.example.service.Person;
import com.example.service.Pet;
interface IPet
{
	List<Pet> getPets(in Person owner);
	
}