/*! Verify-v0.1.0 MIT License by 大熊*/
var image = ['http://pic7.photophoto.cn/20080407/0034034859692813_b.jpg',
			'http://img1.3lian.com/2015/a1/91/d/243.jpg',
			'http://img1.3lian.com/2015/a1/91/d/242.jpg',
			'http://img1.3lian.com/2015/a1/91/d/241.jpg',
			'http://img1.3lian.com/2015/a1/91/d/240.jpg'];

var fortString = '天地玄黄宇宙洪荒日月盈昃辰宿列张'+
				 '寒来暑往秋收冬藏闰余成岁律吕调阳'+
				 '云腾致雨露结为霜金生丽水玉出昆冈'+
				 '剑号巨阙珠称夜光果珍李柰菜重芥姜'+
				 '海咸河淡鳞潜羽翔龙师火帝鸟官人皇'+
				 '始制文字乃服衣裳推位让国有虞陶唐'+
				 '吊民伐罪周发殷汤坐朝问道垂拱平章'+
				 '爱育黎首臣伏戎羌遐迩一体率宾归王'+
				 '鸣凤在竹白驹食场化被草木赖及万方'+
				 '盖此身发四大五常恭惟鞠养岂敢毁伤'+
				 '女慕贞洁男效才良知过必改得能莫忘'+
				 '罔谈彼短靡恃己长信使可覆器欲难量'+
				 '墨悲丝染诗赞羔羊景行维贤克念作圣'+
				 '德建名立形端表正空谷传声虚堂习听'+
				 '祸因恶积福缘善庆尺璧非宝寸阴是竞'+
				 '资父事君曰严与敬孝当竭力忠则尽命'+
				 '临深履薄夙兴温凊似兰斯馨如松之盛'+
				 '川流不息渊澄取映容止若思言辞安定'+
				 '笃初诚美慎终宜令荣业所基籍甚无竟'+
				 '学优登仕摄职从政存以甘棠去而益咏'+
				 '乐殊贵贱礼别尊卑上和下睦夫唱妇随'+
				 '外受傅训入奉母仪诸姑伯叔犹子比儿'+
				 '孔怀兄弟同气连枝交友投分切磨箴规'+
				 '仁慈隐恻造次弗离节义廉退颠沛匪亏'+
				 '性静情逸心动神疲守真志满逐物意移'+
				 '坚持雅操好爵自縻都邑华夏东西二京'+
				 '背邙面洛浮渭据泾宫殿盘郁楼观飞惊'+
				 '图写禽兽画彩仙灵丙舍旁启甲帐对楹'+
				 '肆筵设席鼓瑟吹笙升阶纳陛弁转疑星'+
				 '右通广内左达承明既集坟典亦聚群英'+
				 '杜稿钟隶漆书壁经府罗将相路侠槐卿'+
				 '户封八县家给千兵高冠陪辇驱毂振缨'+
				 '世禄侈富车驾肥轻策功茂实勒碑刻铭'+
				 '盘溪伊尹佐时阿衡奄宅曲阜微旦孰营'+
				 '桓公匡合济弱扶倾绮回汉惠说感武丁'+
				 '俊义密勿多士实宁晋楚更霸赵魏困横'+
				 '假途灭虢践土会盟何遵约法韩弊烦刑'+
				 '起翦颇牧用军最精宣威沙漠驰誉丹青'+
				 '九州禹迹百郡秦并岳宗泰岱禅主云亭'+
				 '雁门紫塞鸡田赤诚昆池碣石钜野洞庭'+
				 '旷远绵邈岩岫杳冥治本于农务兹稼穑'+
				 '俶载南亩我艺黍稷税熟贡新劝赏黜陟'+
				 '孟轲敦素史鱼秉直庶几中庸劳谦谨敕'+
				 '聆音察理鉴貌辨色贻厥嘉猷勉其祗植'+
				 '省躬讥诫宠增抗极殆辱近耻林皋幸即'+
				 '两疏见机解组谁逼索居闲处沉默寂寥'+
				 '求古寻论散虑逍遥欣奏累遣戚谢欢招'+
				 '渠荷的历园莽抽条枇杷晚翠梧桐蚤凋'+
				 '陈根委翳落叶飘摇游鹍独运凌摩绛霄'+
				 '耽读玩市寓目囊箱易輶攸畏属耳垣墙'+
				 '具膳餐饭适口充肠饱饫烹宰饥厌糟糠'+
				 '亲戚故旧老少异粮妾御绩纺侍巾帷房'+
				 '纨扇圆洁银烛炜煌昼眠夕寐蓝笋象床'+
				 '弦歌酒宴接杯举殇矫手顿足悦豫且康'+
				 '嫡后嗣续祭祀烝尝稽颡再拜悚惧恐惶'+
				 '笺牒简要顾答审详骸垢想浴执热愿凉'+
				 '驴骡犊特骇跃超骧诛斩贼盗捕获叛亡'+
				 '布射僚丸嵇琴阮箫恬笔伦纸钧巧任钓'+
				 '释纷利俗并皆佳妙毛施淑姿工颦妍笑'+
				 '年矢每催曦晖朗曜璇玑悬斡晦魄环照'+
				 '指薪修祜永绥吉劭矩步引领俯仰廊庙'+
				 '束带矜庄徘徊瞻眺孤陋寡闻愚蒙等诮'+
				 '谓语助者焉哉乎也'

;(function($, window, document,undefined) {
	
	//定义Code的构造函数
    var Code = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
        	type : 1,
        	figure : 100,	//位数，仅在type=2时生效
        	arith : 0,	//算法，支持加减乘，0为随机，仅在type=2时生效
        	width : '200px',
		    height : '60px',
		    fontSize : '30px',
        	codeLength : 6,
        	btnId : 'check-btn',
        	ready : function(){},
        	success : function(){},
            error : function(){}
        },
        this.options = $.extend({}, this.defaults, opt)
    };
    
    var _code_chars = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    var _code_color1 = ['#fffff0', '#f0ffff', '#f0fff0', '#fff0f0'];
    var _code_color2 = ['#FF0033', '#006699', '#993366', '#FF9900', '#66CC66', '#FF33CC'];
    
    //定义Code的方法
    Code.prototype = {
    	init : function() {
			
			var _this = this;
			
			this.loadDom();
			this.setCode();
			
			this.options.ready();
			if(this.$element[0] == null)
				return;
			this.$element[0].onselectstart = document.body.ondrag = function(){ 
				return false; 
			};
			
			//点击验证码
			this.$element.find('.verify-code, .verify-change-code').on('click', function() {
				_this.setCode();
			});
			
			//确定的点击事件
			this.htmlDoms.code_btn.on('click', function() {
				_this.checkCode();
			})
			
    	},
    	
    	//加载页面
    	loadDom : function() {
    		var panelHtml = '<div class="cerify-code-panel"><div class="verify-code"></div><div class="verify-code-area"><div class="verify-input-area"><input type="text" class="varify-input-code" /></div><div class="verify-change-area"><a class="verify-change-code">换一张</a></div></div></div>';
        	this.$element.append(panelHtml);
        	
        	this.htmlDoms = {
        		code_btn : $('#'+this.options.btnId),
        		code : this.$element.find('.verify-code'),
        		code_area : this.$element.find('.verify-code-area'),
        		code_input : this.$element.find('.varify-input-code'),
        	};
        	
        	this.htmlDoms.code.css({'width':this.options.width, 'height':this.options.height,'line-height':this.options.height, 'font-size':this.options.fontSize});
        	this.htmlDoms.code_area.css({'width':this.options.width});
    	},
    	
    	
    	//设置验证码
    	setCode : function() {
    		
    		var color1Num = Math.floor(Math.random() * 3);
    		var color2Num = Math.floor(Math.random() * 5);
    		
    		this.htmlDoms.code.css({'background-color': _code_color1[color1Num], 'color': _code_color2[color2Num]});
    		this.htmlDoms.code_input.val('');
    		
    		var code = '';
    		this.code_chose = '';
    		
    		if(this.options.type == 1) {		//普通验证码
				for(var i = 0; i < this.options.codeLength; i++) {
					var charNum = Math.floor(Math.random() * 52);
					var tmpStyle = (charNum%2 ==0)? "font-style:italic;margin-right: 10px;":"font-weight:bolder;";
					tmpStyle += (Math.floor(Math.random() * 2) == 1)? "font-weight:bolder;":"";
					
					this.code_chose += _code_chars[charNum];
					code += '<font style="'+tmpStyle+'">'+_code_chars[charNum]+'</font>';
				}
    		}else {		//算法验证码
    			
    			var num1 = Math.floor(Math.random() * this.options.figure);
    			var num2 = Math.floor(Math.random() * this.options.figure);
    			
    			if(this.options.arith == 0) {
    				var tmparith = Math.floor(Math.random() * 3);
    			}
    			
    			switch(tmparith) {
    				case 1 :
    					this.code_chose = parseInt(num1) + parseInt(num2);
    					code = num1 + ' + ' + num2 + ' = ?';
    					break;
    				case 2 :
    					if(parseInt(num1) < parseInt(num2)) {
    						var tmpnum = num1;
    						num1 = num2;
    						num2 = tmpnum;
    					}
    					this.code_chose = parseInt(num1) - parseInt(num2);
    					code = num1 + ' - ' + num2 + ' = ?';
    					break;
    				default :
    					this.code_chose = parseInt(num1) * parseInt(num2);
    					code = num1 + ' × ' + num2 + ' = ?';
    					break;
    			}
    		}
    		
    		this.htmlDoms.code.html(code);
    		
    	},
    	
    	//比对验证码
    	checkCode : function() {
    		if(this.options.type == 1) {		//普通验证码
    			var own_input = this.htmlDoms.code_input.val().toUpperCase();
    			this.code_chose = this.code_chose.toUpperCase();
    		}else {
    			var own_input = this.htmlDoms.code_input.val();
    		}
    		
    		if(own_input == this.code_chose) {
    			this.options.success();
    		}else {
    			this.options.error();
    			this.setCode();
    		}
    	}
    };
    
    
    //定义Slide的构造函数
    var Slide = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
        	
        	type : 1,
        	vOffset: 5,
            vSpace : 5,
            imgName : image,
            imgSize : {
	        	width: '400px',
	        	height: '200px',
	        },
	        blockSize : {
	        	width: '50px',
	        	height: '50px',
	        },
	        barSize : {
	        	width : '400px',
	        	height : '40px',
	        },
            ready : function(){},
        	success : function(){},
            error : function(){}
            
        },
        this.options = $.extend({}, this.defaults, opt)
    };
    
    
    //定义Slide的方法
    Slide.prototype = {
        
        init: function() {
        	var _this = this;
        	
        	//加载页面
        	this.loadDom();
        	this.options.ready();
            if(this.$element[0] == null)
                return;
        	this.$element[0].onselectstart = document.body.ondrag = function(){ 
				return false; 
			};
        	
        	//按下
        	this.htmlDoms.move_block.on('touchstart', function(e) {
        		_this.start(e);
        	});
        	
        	this.htmlDoms.move_block.on('mousedown', function(e) {
        		_this.start(e);
        	});
        	
        	//拖动
            window.addEventListener("touchmove", function(e) {
            	_this.move(e);
            });
            window.addEventListener("mousemove", function(e) {
            	_this.move(e);
            });
            
            //鼠标松开
            window.addEventListener("touchend", function() {
            	_this.end();
            });
            window.addEventListener("mouseup", function() {
            	_this.end();
            });
            
            //刷新
            _this.$element.find('.verify-refresh').on('click', function() {
            	_this.refresh();
            });
        },
        
        //初始化加载
        loadDom : function() {
        	this.img_rand = Math.floor(Math.random() * this.options.imgName.length);			//随机的背景图片
        	
        	var panelHtml = '';
        	var tmpHtml = '';
        	
        	if(this.options.type != 1) {		//图片滑动
        		panelHtml += '<div class="verify-img-panel"><div  class="verify-refresh"><i class="iconfont icon-refresh"></i></div><div class="verify-gap"></div></div>';
        		tmpHtml = '<div  class="verify-sub-block"></div>';
        	}
        	
        	panelHtml += '<div class="verify-bar-area"><span  class="verify-msg">向右滑动完成验证</span><div class="verify-left-bar"><span  class="verify-msg"></span><div  class="verify-move-block"><i  class="verify-icon iconfont icon-right"></i>'+tmpHtml+'</div></div></div>';
        	this.$element.append(panelHtml);
        	
        	this.htmlDoms = {
        		gap : this.$element.find('.verify-gap'),
        		sub_block : this.$element.find('.verify-sub-block'),
        		img_panel : this.$element.find('.verify-img-panel'),
        		bar_area : this.$element.find('.verify-bar-area'),
        		move_block : this.$element.find('.verify-move-block'),
        		left_bar : this.$element.find('.verify-left-bar'),
        		msg : this.$element.find('.verify-msg'),
        		icon : this.$element.find('.verify-icon'),
        		refresh :this.$element.find('.verify-refresh')
        	};
        	
        	this.status = false;	//鼠标状态
        	this.setSize = this.resetSize(this);	//重新设置宽度高度
        	
        	this.htmlDoms.gap.css({'width': this.options.blockSize.width, 'height': this.options.blockSize.height});
        	this.htmlDoms.sub_block.css({'width': this.options.blockSize.width, 'height': this.options.blockSize.height});
        	this.htmlDoms.img_panel.css({'width': this.setSize.img_width, 'height': this.setSize.img_height, 'background': 'url('+this.options.imgName[this.img_rand]+')', 'background-size' : this.setSize.img_width + ' '+ this.setSize.img_height});
        	this.htmlDoms.bar_area.css({'width': this.setSize.bar_width, 'height': this.options.barSize.height, 'line-height':this.options.barSize.height});
        	this.htmlDoms.move_block.css({'width': this.options.barSize.height, 'height': this.options.barSize.height});
        	this.htmlDoms.left_bar.css({'width': this.options.barSize.height, 'height': this.options.barSize.height});
        	
        	this.randSet();
        },
        
        //鼠标按下
        start: function(e) {
        	this.htmlDoms.msg.text('');
        	this.htmlDoms.move_block.css('background-color', '#337ab7');
        	this.htmlDoms.left_bar.css('border-color', '#337AB7');
        	this.htmlDoms.icon.css('color', '#fff');
        	e.stopPropagation();
        	this.status = true;
        	
        },
        
        //鼠标移动
        move: function(e) {
        	if(this.status) {
	            if(!e.touches) {    //兼容移动端
	                var x = e.clientX;
	            }else {     //兼容PC端
	                var x = e.touches[0].pageX;
	            }
	            var bar_area_left = Slide.prototype.getLeft(this.htmlDoms.bar_area[0]); 
	            var move_block_left = x - bar_area_left; //小方块相对于父元素的left值
	            
	            
	            if(this.options.type != 1) {		//图片滑动
	            	if(move_block_left >= this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.blockSize.width)/2) - 2) {
	                	move_block_left = this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.blockSize.width)/2) - 2;
	            	}
	            	
	            	
	            	
	            	
	            	
	            }else {		//普通滑动
	            	if(move_block_left >= this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.barSize.height)/2) + 3) {
	            		this.$element.find('.verify-msg:eq(1)').text('松开验证');
	                	move_block_left = this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.barSize.height)/2) + 3;
	            	}else {
	            		this.$element.find('.verify-msg:eq(1)').text('');
	            	}
	            }
	            
	            
	            if(move_block_left <= 0) {
            		move_block_left = parseInt(parseInt(this.options.blockSize.width)/2);
            	}
	            
	            //拖动后小方块的left值
	            this.htmlDoms.move_block.css('left', move_block_left-parseInt(parseInt(this.options.blockSize.width)/2) + "px");
	            this.htmlDoms.left_bar.css('width', move_block_left-parseInt(parseInt(this.options.blockSize.width)/2) + "px");
	        }
        },
        
        //鼠标松开
        end: function() {
        	
        	var _this = this;
        	
        	//判断是否重合
        	if(this.status) {
        		
        		if(this.options.type != 1) {		//图片滑动
        			
        			var vOffset = parseInt(this.options.vOffset);
		            if(parseInt(this.htmlDoms.gap.css('left')) >= (parseInt(this.htmlDoms.move_block.css('left')) - vOffset) && parseInt(this.htmlDoms.gap.css('left')) <= (parseInt(this.htmlDoms.move_block.css('left')) + vOffset)) {
		            	this.htmlDoms.move_block.css('background-color', '#5cb85c');
		            	this.htmlDoms.left_bar.css({'border-color': '#5cb85c', 'background-color': '#fff'});
		            	this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-check');
		            	this.htmlDoms.refresh.hide();
		            	this.htmlDoms.move_block.unbind('mousedown touchstart');
		            	this.options.success();
		            }else{
		            	this.htmlDoms.move_block.css('background-color', '#d9534f');
		            	this.htmlDoms.left_bar.css('border-color', '#d9534f');
		            	this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-close');
		            	
		            	setTimeout(function () { 
					    	_this.htmlDoms.move_block.animate({'left':'0px'}, 'fast');
					    	_this.htmlDoms.left_bar.animate({'width': '40px'}, 'fast');
					    	_this.htmlDoms.left_bar.css({'border-color': '#ddd'});
					    	
					    	_this.htmlDoms.move_block.css('background-color', '#fff');
					    	_this.htmlDoms.icon.css('color', '#000');
					    	_this.htmlDoms.icon.removeClass('icon-close');
		            		_this.htmlDoms.icon.addClass('icon-right');
		            		_this.$element.find('.verify-msg:eq(0)').text('向右滑动完成验证');
					    	
					    }, 400);
		            	
		            	this.options.error();
		            }
        			
        		}else {		//普通滑动
        			
        			if(parseInt(this.htmlDoms.move_block.css('left')) >= (parseInt(this.setSize.bar_width) - parseInt(this.options.barSize.height) - parseInt(this.options.vOffset))) {
        				this.htmlDoms.move_block.css('background-color', '#5cb85c');
        				this.htmlDoms.left_bar.css({'color': '#4cae4c', 'border-color': '#5cb85c', 'background-color': '#fff' });
        				this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-check');
		            	this.htmlDoms.refresh.hide();
		            	this.htmlDoms.move_block.unbind('mousedown');
		            	this.htmlDoms.move_block.unbind('touchstart');
        				this.$element.find('.verify-msg:eq(1)').text('验证成功');
        				this.options.success();
        			}else {
        				
        				this.htmlDoms.move_block.css('background-color', '#d9534f');
		            	this.htmlDoms.left_bar.css('border-color', '#d9534f');
		            	this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-close');
		            	
		            	setTimeout(function () { 
					    	_this.htmlDoms.move_block.animate({'left':'0px'}, 'fast');
					    	_this.htmlDoms.left_bar.animate({'width': '40px'}, 'fast');
					    	_this.htmlDoms.left_bar.css({'border-color': '#ddd'});
					    	
					    	_this.htmlDoms.move_block.css('background-color', '#fff');
					    	_this.htmlDoms.icon.css('color', '#000');
					    	_this.htmlDoms.icon.removeClass('icon-close');
		            		_this.htmlDoms.icon.addClass('icon-right');
		            		_this.$element.find('.verify-msg:eq(0)').text('向右滑动解锁');
					    	
					    }, 400);
		            	
		            	this.options.error();
        			}
        		}
        		
	            this.status = false;
        	}
        },
        
        
        resetSize : function(obj) {
        	var img_width,img_height,bar_width,bar_height;	//图片的宽度、高度，移动条的宽度、高度
        	var parentWidth = obj.$element.parent().width() || $(window).width();
        	var parentHeight = obj.$element.parent().height() || $(window).height();
        	
       		if(obj.options.imgSize.width.indexOf('%')!= -1){
        		img_width = parseInt(obj.options.imgSize.width)/100 * parentWidth + 'px';
		　　}else {
				img_width = obj.options.imgSize.width;
			}
		
			if(obj.options.imgSize.height.indexOf('%')!= -1){
        		img_height = parseInt(obj.options.imgSize.height)/100 * parentHeight + 'px';
		　　}else {
				img_height = obj.options.imgSize.height;
			}
		
			if(obj.options.barSize.width.indexOf('%')!= -1){
        		bar_width = parseInt(obj.options.barSize.width)/100 * parentWidth + 'px';
		　　}else {
				bar_width = obj.options.barSize.width;
			}
		
			if(obj.options.barSize.height.indexOf('%')!= -1){
        		bar_height = parseInt(obj.options.barSize.height)/100 * parentHeight + 'px';
		　　}else {
				bar_height = obj.options.barSize.height;
			}
		
			return {img_width : img_width, img_height : img_height, bar_width : bar_width, bar_height : bar_height};
       	},
        
        //随机出生点位
        randSet: function() {
        	var rand1 = Math.floor(Math.random()*9+1);
        	var rand2 = Math.floor(Math.random()*9+1);
        	var top = rand1 * parseInt(this.setSize.img_height)/15 + parseInt(this.setSize.img_height) * 0.1;
        	var left = rand2 * parseInt(this.setSize.img_width)/15 + parseInt(this.setSize.img_width) * 0.1;
        	
        	this.$element.find('.verify-img-panel').css('margin-bottom', this.options.vSpace + 'px');
        	this.$element.find('.verify-gap').css({'top': top, 'left': left});
          	this.$element.find('.verify-sub-block').css({'top':'-'+(parseInt(this.setSize.img_height)- top + this.options.vSpace + 2)+'px', 'background-image': 'url(images/'+this.options.imgName[this.img_rand]+')', 'background-size': this.setSize.img_width + ' '+ this.setSize.img_height,'background-position-y': '-'+top+ 'px', 'background-position-x': '-'+left+'px'});
        },
        
        //刷新
        refresh: function() {
        	this.randSet();
        	this.img_rand = Math.floor(Math.random() * this.options.imgName.length);			//随机的背景图片
            this.$element.find('.verify-img-panel').css({'background': 'url('+this.options.imgName[this.img_rand]+')', 'background-size': this.setSize.img_width + ' '+ this.setSize.img_height});
            this.$element.find('.verify-sub-block').css({'background-image': 'url('+this.options.imgName[this.img_rand]+')', 'background-size': this.setSize.img_width + ' '+ this.setSize.img_height});
        },
        
        //获取left值
        getLeft: function(node) {
			var left = $(node).offset().left; 
//          var nowPos = node.offsetParent; 	
//          
//          while(nowPos != null) {　　
//              left += $(nowPos).offset().left;　
//              nowPos = nowPos.offsetParent;　　
//          }
            return left;
        }
    };
    
    
    //定义Points的构造函数
    var Points = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
        	defaultNum : 4,	//默认的文字数量
		    checkNum : 3,	//校对的文字数量
		    vSpace : 5,	//间隔
        	imgName : image,
        	imgSize : {
	        	width: '400px',
	        	height: '200px',
	        },
	        barSize : {
	        	width : '400px',
	        	height : '40px',
	        },
	        ready : function(){},
        	success : function(){},
            error : function(){}
        },
        this.options = $.extend({}, this.defaults, opt)
    };
    
    //定义Points的方法
    Points.prototype = {
    	init : function() {
			
			var _this = this;
			
			//加载页面
        	_this.loadDom();
        	 
        	_this.refresh();
        	_this.options.ready();
            if(this.$element[0] == null)
                return;
        	this.$element[0].onselectstart = document.body.ondrag = function(){ 
				return false; 
			};
        	
		 	//点击事件比对
        	_this.$element.find('.verify-img-panel canvas').on('click', function(e) {
        		
				_this.checkPosArr.push(_this.getMousePos(this, e));
				
				if(_this.num == _this.options.checkNum) {
					
					_this.num = _this.createPoint(_this.getMousePos(this, e));
					setTimeout(function () { 
						var flag = _this.comparePos(_this.fontPos, _this.checkPosArr);
						
						if(flag == false) {	//验证失败
							
							_this.options.error();
							_this.$element.find('.verify-bar-area').css({'color': '#d9534f', 'border-color': '#d9534f'});
						    _this.$element.find('.verify-msg').text('验证失败');
							
							setTimeout(function () { 
								_this.$element.find('.verify-bar-area').css({'color': '#000','border-color': '#ddd'});
						    	_this.refresh();
						    }, 400);
							
						}else {	//验证成功
							_this.$element.find('.verify-bar-area').css({'color': '#4cae4c', 'border-color': '#5cb85c'});
							_this.$element.find('.verify-msg').text('验证成功');
							_this.$element.find('.verify-refresh').hide();
							_this.$element.find('.verify-img-panel').unbind('click');
							_this.options.success();
						}
					}, 400);
					
				}
				
				if(_this.num < _this.options.checkNum) {
					_this.num = _this.createPoint(_this.getMousePos(this, e));
				}

        	});
        	
        	 //刷新
            _this.$element.find('.verify-refresh').on('click', function() {
            	_this.refresh();
            });
        	
    	},
    	
    	
    	
    	//加载页面
    	loadDom : function() {
    		
    		this.fontPos = [];	//选中的坐标信息
    		this.checkPosArr = [];	//用户点击的坐标
    		this.num = 1;	//点击的记数
    		this.img_rand = Math.floor(Math.random() * this.options.imgName.length);			//随机的背景图片
    		
        	var panelHtml = '';
        	var tmpHtml = '';
        	
        	this.setSize = Slide.prototype.resetSize(this);	//重新设置宽度高度
        	
        	panelHtml += '<div class="verify-img-panel"><div  class="verify-refresh" style="z-index:9999"><i class="iconfont icon-refresh"></i></div><canvas width="'+this.setSize.img_width+'" height="'+this.setSize.img_height+'"></canvas></div><div class="verify-bar-area"><span  class="verify-msg"></span></div>';
        	
        	this.$element.append(panelHtml);
        	
        	
        	this.htmlDoms = {
        		img_panel : this.$element.find('.verify-img-panel'),
        		bar_area : this.$element.find('.verify-bar-area'),
        		msg : this.$element.find('.verify-msg'),
        	};
        	
    		this.htmlDoms.img_panel.css({'width': this.setSize.img_width, 'height': this.setSize.img_height, 'background-size' : this.setSize.img_width + ' '+ this.setSize.img_height, 'margin-bottom': this.options.vSpace + 'px'});
    		this.htmlDoms.bar_area.css({'width': this.options.barSize.width, 'height': this.options.barSize.height, 'line-height':this.options.barSize.height});
    		
    	},
    	
    	//绘制合成的图片
    	drawImg : function(obj, img) {
    		//准备canvas环境 
	       	var canvas = this.$element.find('canvas')[0];
	      	//var canvas=document.getElementById("myCanvas");
            if(canvas == null)
                return;
	       	var ctx=canvas.getContext("2d");
	       	
	       	// 绘制图片
	       	ctx.drawImage(img,0,0, parseInt(this.setSize.img_width), parseInt(this.setSize.img_height));
	       	
	       	// 绘制水印
	       	var fontSizeArr = ['italic small-caps bold 20px microsoft yahei', 'small-caps normal 25px arial', '34px microsoft yahei'];
	       	var fontStr = fortString;	//不重复的汉字
	       	
	       	
	       	var fontChars = [];
	       	
	       	var avg = Math.floor(parseInt(this.setSize.img_width)/(parseInt(this.options.defaultNum)+1));
	       	var tmp_index = '';
	       	var color2Num = Math.floor(Math.random() * 5);
	       	
	       	for(var i = 1; i <= this.options.defaultNum; i++) {
	       		
	       		fontChars[i-1] = this.getChars(fontStr, fontChars);
	       		
	       		tmp_index = Math.floor(Math.random()*3);
	       		ctx.font = fontSizeArr[tmp_index];
		       	ctx.fillStyle = _code_color2[color2Num];
		       	
		       	if(Math.floor(Math.random() * 2) == 1) {
		       		var tmp_y = Math.floor(parseInt(this.setSize.img_height)/2) + tmp_index*20 + 20;
		       	}else {
		       		var tmp_y = Math.floor(parseInt(this.setSize.img_height)/2) - tmp_index*20;
		       	}
		       	
		       	ctx.fillText(fontChars[i-1],avg * i, tmp_y);
		       	this.fontPos[i-1] = {'char': fontChars[i-1], 'x': avg * i, 'y': tmp_y};
		       	
	       	}
	       	
	       	for(var i = 0; i < (this.options.defaultNum-this.options.checkNum); i++) {
	       		this.shuffle(this.fontPos).pop();
	       	}
	       	
	       	var msgStr = '';
	       	for(var i = 0; i < this.fontPos.length; i++) {
	       		msgStr += this.fontPos[i].char + ',';
	       	}
	       	
	       	this.htmlDoms.msg.text('请顺序点击【' + msgStr.substring(0,msgStr.length-1) + '】');
	       	
	       	return this.fontPos;
    	},
    	
    	//获取坐标
    	getMousePos :function(obj, event) {
            var e = event || window.event;
            var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
            var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
            var x = e.clientX - ($(obj).offset().left - $(window).scrollLeft());
    		var y = e.clientY - ($(obj).offset().top - $(window).scrollTop());
    		
            return {'x': x, 'y': y};
     	},
     	
     	//递归去重
     	getChars : function(fontStr, fontChars) {
     		
     		var tmp_rand = parseInt(Math.floor(Math.random() * fontStr.length));
     		if(tmp_rand > 0) {
     			tmp_rand = tmp_rand - 1;
     		}
     		
     		tmp_char = fontStr.charAt(tmp_rand);
       		if($.inArray(tmp_char, fontChars) == -1) {
       			return tmp_char;
       		}else {
       			return Points.prototype.getChars(fontStr, fontChars);
       		}
     	},
		
		//洗牌数组
       	shuffle : function(arr) {
			var m = arr.length, i;
			while (m) {
				i = (Math.random() * m--) >>> 0;
				[arr[m], arr[i]] = [arr[i], arr[m]]
			}
			return arr;
		},
       	
       	//创建坐标点
       	createPoint : function (pos) {
       		this.htmlDoms.img_panel.append('<div class="point-area" style="background-color:#1abd6c;color:#fff;z-index:9999;width:30px;height:30px;text-align:center;line-height:30px;border-radius: 50%;position:absolute;top:'+parseInt(pos.y-10)+'px;left:'+parseInt(pos.x-10)+'px;">'+this.num+'</div>');
       		return ++this.num;
       	},
       	
       	//比对坐标点
       	comparePos : function (fontPos, checkPosArr) {
       		
       		var flag = true;
       		for(var i = 0; i < fontPos.length; i++) {
       			if(!(parseInt(checkPosArr[i].x) + 40 > fontPos[i].x && parseInt(checkPosArr[i].x) - 40 < fontPos[i].x && parseInt(checkPosArr[i].y) + 40 > fontPos[i].y && parseInt(checkPosArr[i].y) - 40 < fontPos[i].y)) {
       				flag = false;
       				break;
       			}
       		}
       		
       		return flag;
       	},
       	
       	//刷新
        refresh: function() {
        	var _this = this;
        	this.$element.find('.point-area').remove();
        	this.fontPos = [];
        	this.checkPosArr = [];
        	this.num = 1;
        	
        	this.img_rand = Math.floor(Math.random() * this.options.imgName.length);			//随机的背景图片
        	var img = new Image();
		    img.src = this.options.imgName[this.img_rand];
		 	
		 	
		 	// 加载完成开始绘制
		 	$(img).on('load', function(e) {
        		this.fontPos = _this.drawImg(_this, this);
        	});

        },
    	
    };
    
    //在插件中使用codeVerify对象
    $.fn.codeVerify = function(options, callbacks) {
        var code = new Code(this, options);
        code.init();
    };
    
    //在插件中使用slideVerify对象
    $.fn.slideVerify = function(options, callbacks) {
        var slide = new Slide(this, options);
        slide.init();
    };
    
    //在插件中使用clickVerify对象
    $.fn.pointsVerify = function(options, callbacks) {
        var points = new Points(this, options);
        points.init();
    };
   
})(jQuery, window, document);


$('#mpanel2').codeVerify({
		type : 1,
		width : '400px',
		height : '50px',
		fontSize : '30px',
		codeLength : 6,
		btnId : 'check-btn',
		ready : function() {
		},
		success : function() {
			alert('验证匹配！');
		},
		error : function() {
			alert('验证码不匹配！');
		}
	});
	
	
	$('#mpanel3').codeVerify({
		type : 2,
		figure : 100,	//位数，仅在type=2时生效
		arith : 0,	//算法，支持加减乘，不填为随机，仅在type=2时生效
		width : '200px',
		height : '50px',
		fontSize : '30px',
		btnId : 'check-btn2',
		ready : function() {
		},
		success : function() {
			alert('验证匹配！');
		},
		error : function() {
			alert('验证码不匹配！');
		}
	});
	
	
	
	$('#mpanel1').slideVerify({
		type : 1,		//类型
		vOffset : 5,	//误差量，根据需求自行调整
		barSize : {
			width : '80%',
			height : '40px',
		},
		ready : function() {
		},
		success : function() {
			alert('验证成功，添加你自己的代码！');
			//......后续操作
		},
		error : function() {
//		        	alert('验证失败！');
		}
		
	});
	
	
	$('#mpanel4').slideVerify({
		type : 2,		//类型
		vOffset : 5,	//误差量，根据需求自行调整
		vSpace : 5,	//间隔
		imgName : image,
		imgSize : {
			width: '400px',
			height: '200px',
		},
		blockSize : {
			width: '40px',
			height: '40px',
		},
		barSize : {
			width : '400px',
			height : '40px',
		},
		ready : function() {
		},
		success : function() {
			alert('验证成功，添加你自己的代码！');
			//......后续操作
		},
		error : function() {
//		        	alert('验证失败！');
		}
		
	});
	
	
	
	$('#mpanel5').pointsVerify({
		defaultNum : 4,	//默认的文字数量
		checkNum : 2,	//校对的文字数量
		vSpace : 5,	//间隔
		imgName : image,
		imgSize : {
			width: '600px',
			height: '200px'
		},
		barSize : {
			width : '600px',
			height : '40px'
		},
		ready : function() {
		},
		success : function() {
			action();
			//......后续操作
		},
		error : function() {
//		        	alert('验证失败！');
		}
		
	});


	
	$('#mpanel6').pointsVerify({
		defaultNum : 4,	//默认的文字数量
		checkNum : 2,	//校对的文字数量
		vSpace : 5,	//间隔
		imgName : image,
		imgSize : {
			width: '600px',
			height: '200px'
		},
		barSize : {
			width : '600px',
			height : '40px'
		},
		ready : function() {
		},
		success : function() {
			alert('验证成功，添加你自己的代码！');
			//......后续操作
		},
		error : function() {
//		        	alert('验证失败！');
		}
		
	});

