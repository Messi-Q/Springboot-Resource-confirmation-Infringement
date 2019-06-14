package team.educoin.transaction.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import team.educoin.transaction.pojo.AgencyConsumerInfo;
import team.educoin.transaction.pojo.FileInfo;
import team.educoin.transaction.pojo.UserConsumeInfo;

@Mapper
@Component
public interface TransactionMapper {

    // 根据id修改资源的fileOwner(机构用户)
    @Update("update file_info set fileOwner=#{fileOwner} where id=#{id}")
    int updateAgencyConsumer(FileInfo fileInfo);

    // 生成普通用户消费表
    @Insert("insert into person_consume (email, serviceId, fileTitle, fileReadPrice, fileName) values (#{email}" +
            ",#{serviceId},#{fileTitle},#{fileReadPrice},#{fileName})")
    int insertCommonConsume(UserConsumeInfo userConsumeInfo);

    //生成机构用户消费表
    @Insert("insert into agency_consume (id, email, serviceId, fileTitle, fileOwnerShipPrice, fileName) values (#{id},#{email}" +
            ",#{serviceId},#{fileTitle},#{fileOwnerShipPrice},#{fileName})")
    int insertAgencyConsume(AgencyConsumerInfo agencyConsumerInfo);
}
