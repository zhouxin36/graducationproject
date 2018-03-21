package com.zx.backstage.controller;

import com.zx.api.bean.*;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.DeleteFileUtil;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.AdminService;
import com.zx.backstage.service.CategoryService;
import com.zx.backstage.service.PicService;
import com.zx.backstage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    PicService picService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    AdminService adminService;

    @Value("${file_base_url}")
    String FILE_BASE_URL;


    @ResponseBody
    @RequestMapping("/product_list")
    public R productList(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        logger.info("---->ProductController/productList; params:{}", params);
        PageUtils pageUtil = null;
        try {
            Query query = new Query(params);
            ProductExample productExample = new ProductExample();
            productExample.setStartRow((Integer) query.get("offset"));
            productExample.setPageSize((Integer) query.get("limit"));
            ProductExample.Criteria criteria = productExample.createCriteria();
            if (!MyUtils.isBlank((String) query.get("name")))
                criteria.andNameLike("%" + (String) query.get("name") + "%");
            if (!MyUtils.isBlank((String) query.get("type")))
                criteria.andCategoryIdEqualTo((String) query.get("type"));
            List<Product> products = productService.selectByExample(productExample);
            long total = productService.countByExample(productExample);
            pageUtil = new PageUtils(products, (int) total, query.getLimit(), query.getPage());
            return R.ok().put("page", pageUtil);
        } catch (Exception e) {
            logger.error("ProductController/productList; e:{},pageUtil:{}", e, pageUtil);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/product_select_category")
    public R productSelectCategory(HttpServletRequest request) {
        logger.info("---->ProductController/productSelectCategory; ");
        Map<String, List<Category>> map = new HashMap<>();
        List<Category> categories = null;
        try {
            @SuppressWarnings("unchecked")
            List<Category> list = (List<Category>) request.getSession().getAttribute("category");
            if (list != null) {
                map.put("list", list);
                return R.ok().put("list", list);
            } else {
                categories = categoryService.selectByExample(new CategoryExample());
                request.getSession().setAttribute("category", categories);
                map.put("list", categories);
                return R.ok().put("list", categories);
            }
        } catch (Exception e) {
            logger.error("ProductController/productSelectCategory; e:{},categories:{}", e, categories);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/product_add")
    public ResultDTO productAdd(HttpServletRequest request, @RequestBody Product product)
            throws IllegalStateException, IOException {
        try {
            logger.info("---->ProductController/productAdd; product:{}", product);
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            product.setAddDate(LocalDateTime.now());
            product.setMonthSale(0);
            product.setSale(0);
            product.setOpen(2);
            product.setLastAdminId(admin.getId());
            product.setId(MyUtils.getUUID());
            int i = productService.insert(product);
//		int i = 1;
            if (i == 1) {
                return ResultDTO.ok("添加成功");

            } else {
                return ResultDTO.error("添加失败");
            }
        } catch (Exception e) {
            logger.error("ProductController/productAdd; e:{},product:{}", e, product);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/request_img")
    public ResultDTO<Map<String, Object>> requestImg(HttpServletRequest request, String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/requestImg; id:{}", id);
            String path = FILE_BASE_URL;
            PicExample picExample = new PicExample();
            PicExample.Criteria criteria = picExample.createCriteria();
            criteria.andProductIdEqualTo(id);
            List<Pic> list = picService.selectByExample(picExample);
            ResultDTO msg = ResultDTO.ok();
            List<Pic> list2 = new ArrayList<Pic>();
            List<Pic> list3 = new ArrayList<Pic>();
            for (Pic pic : list) {
                if (pic.getType() == 1) {
                    map.put("type1", pic);
                }
                if (pic.getType() == 2) {
                    list2.add(pic);
                }
                if (pic.getType() == 3) {
                    list3.add(pic);
                }
            }
            map.put("type2", list2);
            map.put("type3", list3);
            map.put("p", path);
            msg.setData(map);
            return msg;
        } catch (Exception e) {
            logger.error("ProductController/requestImg; e:{},map:{}", e, map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object handleFileUpload(HttpServletRequest request, String id, Integer type) {
        try {
            logger.info("---->ProductController/handleFileUpload; id:{},type:{}", id, type);
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            MultipartFile file = null;
            BufferedOutputStream stream = null;
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    byte[] bytes = file.getBytes();
                    String name = id + "_" + file.getOriginalFilename();
                    if (type == 1) {
                        PicExample picExample = new PicExample();
                        PicExample.Criteria criteria1 = picExample.createCriteria();
                        criteria1.andProductIdEqualTo(id);
                        criteria1.andTypeEqualTo(1);
                        List<Pic> list = picService.selectByExample(picExample);
                        Pic pic = new Pic();
                        pic.setType(1);
                        pic.setProductId(id);
                        pic.setPath(name);
                        pic.setId(MyUtils.getUUID());
                        if (list.size() != 0) {
                            picExample = new PicExample();
                            PicExample.Criteria criteria = picExample.createCriteria();
                            criteria.andTypeEqualTo(1);
                            criteria.andProductIdEqualTo(id);
                            picService.deleteByExample(picExample);
                            picService.insert(pic);
                            DeleteFileUtil.delete(FILE_BASE_URL, list.get(0).getPath());
                        } else {
                            picService.insert(pic);
                        }
                    } else {
                        Pic pic = new Pic();
                        pic.setType(type);
                        pic.setProductId(id);
                        pic.setPath(name);
                        pic.setId(MyUtils.getUUID());
                        picService.insert(pic);
                    }
                    stream = new BufferedOutputStream(new FileOutputStream(new File(FILE_BASE_URL + name)));
                    stream.write(bytes);
                    stream.close();
                } else {
                    logger.error("ProductController/handleFileUpload;" + "You failed to upload " + i + " becausethe file was empty.");
                }
            }
            return R.ok();
        } catch (Exception e) {
            logger.error("ProductController/handleFileUpload; e:{}", e);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/find_img")
    public R findImg(String id) {
        try {
            logger.info("---->ProductController/findImg; id:{}", id);
            String path = FILE_BASE_URL;
            PicExample picExample = new PicExample();
            PicExample.Criteria criteria = picExample.createCriteria();
            criteria.andProductIdEqualTo(id);
            List<Pic> list = picService.selectByExample(picExample);
            if (list.size() != 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("list", list);
                List<Pic> list1 = new ArrayList<>();
                List<Pic> list2 = new ArrayList<>();
                List<Pic> list3 = new ArrayList<>();
                for (Pic pic1 :
                        list) {
                    if (pic1.getType() == 1) {
                        list1.add(pic1);
                    }
                    if (pic1.getType() == 2) {
                        list2.add(pic1);
                    }
                    if (pic1.getType() == 3) {
                        list3.add(pic1);
                    }
                }
                return R.ok().put("list1", list1).put("list2", list2).put("list3", list3);
            } else {
                return R.error("图片为空");
            }
        } catch (Exception e) {
            logger.error("ProductController/findImg; e:{}", e);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/delete_pic")
    public R deletePic(String key) {
        try {
            logger.info("---->ProductController/deletePic; key:{}", key);
            String path = FILE_BASE_URL;
            PicExample picExample = new PicExample();
            PicExample.Criteria criteria = picExample.createCriteria();
            criteria.andPathEqualTo(key);
            picService.deleteByExample(picExample);
            DeleteFileUtil.delete(FILE_BASE_URL, key);
            return R.ok();
        } catch (Exception e) {
            logger.error("ProductController/deletePic; e:{}", e);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/update_product")
    public ResultDTO updateProduct(HttpServletRequest request, @RequestBody Product product) {
        try {
            logger.info("---->ProductController/updateProduct; product:{}", product);
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            product.setLastAdminId(admin.getId());
            int i = productService.updateByPrimaryKeySelective(product);
            if (i == 1) {
                return ResultDTO.ok("修改成功");
            } else {
                return ResultDTO.error("修改失败");
            }
        } catch (Exception e) {
            logger.error("ProductController/updateProduct; e:{}", e);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }


    @ResponseBody
    @RequestMapping("/select_product")
    public R selectProduct(String id) {
        try {
            logger.info("---->ProductController/selectProduct; id:{}", id);
            Product product = productService.selectByPrimaryKey(id);
            Category category = categoryService.selectByPrimaryKey(product.getCategoryId());
            Admin admin = adminService.selectByPrimaryKey(product.getLastAdminId());
            product.setLastAdminId(admin.getLogin());
            if (product != null) {
                return R.ok().put("app", product).put("cateType", category.getType());
            } else {
                return R.error("此商品不存在");
            }
        } catch (Exception e) {
            logger.error("ProductController/selectProduct; e:{}", e);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/product_open")
    public ResultDTO productOpen(HttpServletRequest request, String id) {
        try {
            logger.info("---->ProductController/productOpen; id:{}", id);
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            Product product = productService.selectByPrimaryKey(id);
            product.setOpen(1);
            product.setLastAdminId(admin.getId());
            int i = productService.updateByPrimaryKey(product);
            if (i == 1) {
                return ResultDTO.ok();
            } else {
                return ResultDTO.error();
            }
        } catch (Exception e) {
            logger.error("ProductController/productOpen; e:{}", e);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }


    @ResponseBody
    @RequestMapping("/product_down")
    public ResultDTO productDown(HttpServletRequest request, String id) {
        try {
            logger.info("---->ProductController/productDown; id:{}", id);
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            Product product = productService.selectByPrimaryKey(id);
            product.setOpen(2);
            product.setLastAdminId(admin.getId());
            int i = productService.updateByPrimaryKey(product);
            if (i == 1) {
                return ResultDTO.ok();
            } else {
                return ResultDTO.error();
            }
        } catch (Exception e) {
            logger.error("ProductController/productDown; e:{}", e);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/product_delete")
    public ResultDTO productDelete(String id) {
        try {
            logger.info("---->ProductController/productDown; id:{}", id);
            PicExample picExample = new PicExample();
            PicExample.Criteria criteria = picExample.createCriteria();
            criteria.andProductIdEqualTo(id);
            List<Pic> list = picService.selectByExample(picExample);
            String path = FILE_BASE_URL;
            for (Pic pic : list) {
                DeleteFileUtil.delete(path, pic.getPath());
                picService.deleteByPrimaryKey(pic.getId());
            }
            int i = productService.deleteByPrimaryKey(id);
            if (i == 1) {
                return ResultDTO.ok("删除成功");
            } else {
                return ResultDTO.error("删除失败");
            }
        } catch (Exception e) {
            logger.error("ProductController/productDelete; e:{}", e);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }
}
