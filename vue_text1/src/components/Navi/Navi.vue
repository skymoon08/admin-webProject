<template>

  <el-container class="container" >

        <el-header  class="header" style="background-color:#545c64">

          <el-menu  default-active="1" router="true"  class="el-menu-demo"  mode="horizontal"  @select="handleSelectTop"  background-color="#545c64"  text-color="#fff" active-text-color="#ffd04b">
             <el-col span="4" class="logo" >
             <a href="/" style="text-decoration:none;color:#FFFFFF;" >OAS认证系统</a>
             </el-col>
                 <el-menu-item v-for="topItem in topMenuItems" v:bind:index="topItem.index"  :route="{path: topItem.router}"><i v-bind:class="topItem.icon"></i>{{topItem.title}}</el-menu-item>

                 <span class="demonstration" style="float:right">
                   <el-dropdown trigger="click" @command="handleCommand">
                     <span id="uid"  class="el-dropdown-link" style="color:white">
                       {{admin}}<i class="el-icon-caret-bottom el-icon--right"></i>
                     </span>
                     <el-dropdown-menu slot="dropdown">
                       <el-dropdown-item command="personal">个人信息</el-dropdown-item>
                       <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                     </el-dropdown-menu>
                   </el-dropdown>
               </span>
           </el-menu>
</el-header>
        <div style="margin-top:5px">
            <el-row :gutter="10">
                <el-col :xs="4" :sm="4" :md="4" :lg="4">
                    <div id="leftmenu1">
                        <el-menu default-active="1" rourouter="true"ter class="el-menu-demo" style="min-height:800px" @select="handleSelect">

                            <el-menu-item v-for="item in menuItems"  v-bind:index="item.index"  :route="{path: item.router}"><i v-bind:class="item.icon"></i>{{item.itemTitle}}</el-menu-item>

                        </el-menu>
                    </div>
                </el-col>
                <el-col :xs="20" :sm="20" :md="20" :lg="20">
                    <div>


                          <el-tabs v-model="editableTabsValue2" type="card" closable @tab-remove="removeTab">
                        <el-tab-pane
                          v-for="(item, index) in editableTabs2"
                          :key="item.name"
                          :label="item.title"
                          :name="item.name"
                        >
                          {{item.content}}
                        </el-tab-pane>
                      </el-tabs>


                    </div>

                    <div style="margin-top:10px">
                        <router-view></router-view>
                    </div>
                </el-col>
            </el-row>
        </div>
    </el-container>

</template>
<style scoped="scoped" lang="scss">
    .container {
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;
        .header {
            height: 60px;
            width:100%;
            line-height: 60px;
            color: #545c64;
            .logo {
                height: 60px;
                font-size: 22px;
                padding-left: 20px;
            }
            .logo-width {
                width: 230px;
            }
        }
}

</style>

<script type="text/ecmascript-6">
import axios from 'axios'

    export default {
      data(){

          return {
              topMenuItems: [],
              admin: '',
              editableTabs2: [],
              tabIndex: '',
              leftMenuItems: [],
          }
      },
      created(){
        this.$axios.get('/getUser')
        .then((response)=>{
            this.admin = response.data.user_id+""+response.data.user_name;
            this.topMenuItems=[{
              index:response.data.topTitle.id,
              icon:response.data.topTitle.icon,
              router:response.data.topTitle.url,
              title:response.data.topTitle.toptitle_name,
            }];
            console.log(this.topMenuItems);
           this.leftMenuItems=[{
             index:response.data.topTitle.leftMenu.id,
             icon:response.data.topTitle.leftMenu.icon,
             itemTitle:response.data.topTitle.leftMenu.leftmenu_name,
             router:response.data.topTitle.leftMenu.url,
           }]
           console.log(this.leftMenuItems);
        })
        .catch(function(err){
          window.location.href="http://new.admin.ads.oppoer.me";
        });

    },
    methods:{
          handleCommand(command) {
                switch(command){
                  case 'logout':
                  window.location.href="http://new.admin.ads.oppoer.me/logout";
                }
                },
            handleIconClick(ev) {
                console.log(ev);
            },

      addTab(targetName) {
        let newTabName = ++this.tabIndex + '';
        this.editableTabs2.push({
          title: 'New Tab',
          name: newTabName,
          content: 'New Tab content'
        });
        this.editableTabsValue2 = newTabName;
      },
      removeTab(targetName) {
        let tabs = this.editableTabs2;
        let activeName = this.editableTabsValue2;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }
        this.editableTabsValue2 = activeName;
        this.editableTabs2 = tabs.filter(tab => tab.name !== targetName);
    },
        handleSelectTop(key, keyPath){
            switch(key){
                case key:
                  //   this.$router.push('/page1');
                    this.menuItems  = this.leftMenuItems;
                    console.log(this.leftMenuItems);

                  //   [
                  //     {index:'1-1',icon:'el-icon-message',itemTitle:'导航1-1'},
                  //     {index:'1-2',icon:'el-icon-menu',itemTitle:'导航1-2'},
                  //     {index:'1-3',icon:'el-icon-setting',itemTitle:'导航1-3'}
                  //   ]
                    break;

            }
        },

            handleSelect(key, keyPath){
                switch(key){
                    case key:
                      //  this.$router.push('/page1')
                        this.breadcrumbItems  = ['导航一']
                      //  this.options.$methods.addTab("页面一");
                        // let newTabName = ++this.tabIndex + '';
                        // this.editableTabs2.push({
                        //   title: 'New Tab',
                        //   name: newTabName,
                        //   content: 'New Tab content'
                        // });
                        // this.editableTabsValue2 = newTabName;
                        break;

                }
            },
        },
    }
</script>
