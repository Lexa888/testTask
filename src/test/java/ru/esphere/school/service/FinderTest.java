package ru.esphere.school.service;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FinderTest {
    Finder finder;
    Set<String> responseOldMembers;
    Set<String> responseYoungMembers;
    List<MembersGroup> membersGroupList;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Member aleksey = new Member("Aleksey", 27);
        Member pavel = new Member("Pavel", 22);
        Member valera = new Member("Valera", 28);
        Member danila = new Member("Danila", 21);
        Member kirill = new Member("Kirill", null);
        List<Member> members1 = new ArrayList<>();
        List<Member> members2 = new ArrayList<>();
        members1.add(aleksey);
        members1.add(pavel);
        members2.add(valera);
        members2.add(danila);
        members2.add(kirill);
        MembersGroup membersGroup1 = new MembersGroup("First", members1);
        MembersGroup membersGroup2 = new MembersGroup("Second", members2);

        membersGroupList = new ArrayList<>();
        membersGroupList.add(membersGroup1);
        membersGroupList.add(membersGroup2);

        finder = new Finder();

        responseOldMembers = new HashSet<>();
        responseOldMembers.add(aleksey.getName());
        responseOldMembers.add(valera.getName());

        responseYoungMembers = new HashSet<>();
        responseYoungMembers.add(pavel.getName());
        responseYoungMembers.add(danila.getName());
    }

    @org.junit.jupiter.api.Test
    void findOldMembers() {
        assertEquals(responseOldMembers, finder.findOldMembers(membersGroupList, 25));
    }

    @org.junit.jupiter.api.Test
    void findYoungMembers() {
        assertEquals(responseYoungMembers, finder.findYoungMembers(membersGroupList, 25));
    }
}