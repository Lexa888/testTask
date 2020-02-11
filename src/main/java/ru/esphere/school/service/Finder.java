package ru.esphere.school.service;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Finder {
    /**
     * Поиск групп людей старше определенного возраста. *
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
}