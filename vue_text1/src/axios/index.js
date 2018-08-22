import Vue from 'vue';
/*引入资源请求插件*/
import axios from 'axios';


/*使用axios插件*/
Vue.prototype.$axios = axios;
axios.defaults.withCredentials=true;
export default({
});
