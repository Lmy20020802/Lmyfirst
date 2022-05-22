package com.example.service;

import com.example.dao.UserRepository;
import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 增加或更新用户信息 如果id不存在则会执行添加 id存在执行更新(框架自动实现)
     * @param user
     */

    public void save(User user) {
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.setCreateTime(now);
        userRepository.save(user);
    }

    /**
     * 根据Id删除用户信息
     * @param id
     */
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 根据Id查询用户信息
     * @param id
     * @return
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 实现分页查询
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    public Page<User> findPage(Integer pageNum, Integer pageSize, String name) {
        // 构建分页查询条件
        Sort sort = new Sort(Sort.Direction.DESC, "create_time");
        Pageable pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        return userRepository.findByNameLike(name, pageRequest);
    }
}
