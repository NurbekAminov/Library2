package org.example.mapper;

import org.example.dto.ProfileDto;
import org.example.enums.Role;
import org.example.enums.StatusProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper {
//    @Override
//    public ProfileDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//        ProfileDto profileDto = new ProfileDto();
//        profileDto.setId(resultSet.getInt("id"));
//        profileDto.setName(resultSet.getString("name"));
//        profileDto.setSurname(resultSet.getString("surname"));
//        profileDto.setPhone(resultSet.getString("phone"));
//        profileDto.setLogin(resultSet.getString("login"));
//        profileDto.setPassword(resultSet.getString("password"));
//        profileDto.setStatusProfile(StatusProfile.valueOf(resultSet.getString("status")));
//        profileDto.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
//        profileDto.setRole(Role.valueOf(resultSet.getString("role")));
//        return profileDto;
//    }
}
