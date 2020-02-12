package ru.esphere.school.service;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Finder {
    /**
     * Поиск групп людей старше определенного возраста.
     *
     * @param groups    группы
     * @param targetAge возраст для поиска
     * @return список имен групп из списка групп старше возраста targetAge
     */
    public Set<String> findOldMembers(List<MembersGroup> groups, int targetAge) {
        return groups.stream()
                .flatMap(x -> x.getMembers().stream()
                        .filter(member -> member.getAge() != null && member.getAge() > targetAge)
                        .map(Member::getName))
                .collect(Collectors.toSet());
    }

    /**
     * Поиск групп людей моложе определенного возраста.
     *
     * @param groups    группы
     * @param targetAge возраст для поиска
     * @return список имен групп из списка групп моложе возраста targetAge
     */
    public Set<String> findYoungMembers(List<MembersGroup> groups, int targetAge) {
        return groups.stream()
                .flatMap(x -> x.getMembers().stream()
                        .filter(member -> member.getAge() != null && member.getAge() < targetAge)
                        .map(Member::getName))
                .collect(Collectors.toSet());
    }

    /**
     * Поиск групп людей по имени или его части.
     *
     * @param groups    группы
     * @param targetName имя или часть имени для поиска
     * @return список имен групп из списка групп совпавшим или частично совпавшим с targetName
     */
    public Set<String> findMembersByName(List<MembersGroup> groups, String targetName) {
        return groups.stream()
                .flatMap(x -> x.getMembers().stream()
                .filter(member -> member.getName() != null && member.getName().toLowerCase().contains(targetName))
                .map(Member::getName))
                .collect(Collectors.toSet());
    }
}