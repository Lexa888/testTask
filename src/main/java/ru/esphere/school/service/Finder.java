package ru.esphere.school.service;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
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
        return action(groups, member -> member.getAge() != null && member.getAge() > targetAge);
    }

    /**
     * Поиск групп людей моложе определенного возраста.
     *
     * @param groups    группы
     * @param targetAge возраст для поиска
     * @return список имен групп из списка групп моложе возраста targetAge
     */
    public Set<String> findYoungMembers(List<MembersGroup> groups, int targetAge) {
        return action(groups, member -> member.getAge() != null && member.getAge() < targetAge);
    }

    /**
     * Поиск групп людей по имени или его части.
     *
     * @param groups     группы
     * @param targetName имя или часть имени для поиска
     * @return список имен групп из списка групп совпавшим или частично совпавшим с targetName
     */
    public Set<String> findMembersByName(List<MembersGroup> groups, String targetName) {
        return action(groups, member -> member.getName() != null && member.getName().toLowerCase().contains(targetName));
    }

    /**
     * Поиск групп людей определенного возраста.
     *
     * @param groups группы
     * @param minAge минимальный возраст для поиска
     * @param maxAge максимальный возраст для поиска
     * @return список имен групп из списка групп в диапазоне возраста minAge и maxAge
     */
    public Set<String> findRangeYearsMembers(List<MembersGroup> groups, int minAge, int maxAge) {
        return action(groups, member -> member.getAge() != null && member.getAge() > minAge && member.getAge() < maxAge);
    }

    /**
     * Вспомогательный метод для сокращения повторяющегося кода
     *
     * @param groups группы
     * @param func предикат
     * @return список имен групп из списка групп
     */
    private static Set<String> action(List<MembersGroup> groups, Predicate<Member> func) {
        return groups.stream()
                .flatMap(x -> x.getMembers().stream()
                        .filter(func)
                        .map(Member::getName))
                .collect(Collectors.toSet());
    }
}


