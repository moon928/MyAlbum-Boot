package cn.yan_wm.myalbum.service.tools.controller;

import cn.yan_wm.myalbum.commons.module.FastDFSFile;
import cn.yan_wm.myalbum.service.tools.utils.FastDFSUtils;
import io.swagger.annotations.Api;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fastDFSDemo
 * @description: FastDFSController
 * @author: yan_zt
 * @create: 2019-12-27 14:36
 */
@RestController
@RequestMapping("/fastDFS")
@Api(tags = "文件上传服务")
public class FastDFSController {

    @PostMapping(value = "/upload")
    public String upload(FastDFSFile dfsFile){
        dfsFile.setAuthor("user");
        dfsFile.setBase64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QA/wD/AP+gvaeTAAAAB3RJTUUH4wEJAC4nsmEZ8QAAEBtJREFUeNrtnWmUHFUVgL+qnpnMJGRCEmISCCQRCUFJgoqiieCBQ46QczDBKCJ7MO5LXMBAUDZZBAEVF1SigB5xgQhuARfcQUTQgAhBwYSQZEgkgdkz0z1d/rhVmTevq6qru2vrnvrO6ROorqmuqnvffe/d++59kJGRkZGRkTEaMZK+gSgZHDeuovOHJprk5zST21GkqaOAuaeIlTNo6e5N+lEioynpGwiDAILOAWOAZvtj2s9u2t8XsSjQZFjF8WbR7LMGip3mHmPAKlD0vn5Lb/0rRl1aAB+BNwHtwGRglv3ZH5gOTAEmAPsiStDOcAMoAF3WGKNY3NfMm93FnUa/tR2LbcB2YLP92QV02eeXUI8KUTcK4CF0ExH2ocARwHzgcOAgYDwwltqtXAHoA7qBLcA/7c8G4ClEKYr6H9WLMtSFAmjCbwYOAN4AvAk4CjgYad1mxRevjiLQCTwDPAjcb/+7Fc06pF0R6k0B5gMfBt4MzET69TSwB7EOfwB+DjwAvOB8mWYlqLdBYBfwMmBO0jei0Wrf0xzgNOAx4A7gG0j3kVpSYwHWnnZPybGVt58IlHQB04HPAacjo/u0sg3pnral2QIkrgCa4HPI6PxF54CHEkwGLgdWAi1JP4MHW4E3AltVBdhx1f4jTpq6ZnuiN5mYAri0+JnAuUj/fg2w9wQPJRgPrAFWAW1JPYcPgRTAISlFiGvUPAJN+PsAZwE/Bj6DKMDXgLdjK6hzvmZKuxErcIX93/XEBGCJ/eyAt2JETawWwKXVLwA+BSxD5uwqzwMXAt8FhmDYEsAIa9ACvBe4DJgU5/OUwc8CHAj8Gvgb8Hlk0LiXOK1BbBZAE34rcDbwQ2TUPNblT6YB1wMfwO7n1WsoL3UQuAn4JNAR1/PUiIW0/jPsd3CW/U6AeK1BLAqgCX8GcB3wFcSD58ck4CrgPOx+fu1p97h1CUPAbcBHEJdtPWDZ/84Fvgpcizi4gPiUIHIF0IT/euAW4EMo/V8ZxgOfBi5GZggjrqsogQWsQyzGxqifK2T2QRxctwBHOgfjUIJIFUAT/knAd4Djq7hUG2Lir0amgCOurw0O7wXeA/wjymeLAANYjIx5ltr/H7kSRKYAivBzSF93E+VNvh/NwPuAGxBn0Ijf0ZTgz4gS3B/V80XIXORdrbSfOVIliEQBFOG3AO8HvojSv9VADjgT6TNn67+nKcEjiBL8MopnjJjpyDjpY9jxjqiUIHQFUITfBpyPZrZDwABORlrJYfrvakrwJDIm+DHDg656oR24BJkmt0E0ShCqAijCb7Vv/CJkEBcFbwFuBl6r/76mBJuQAdZef0IdMQ7xhZxHRJYgNAXQzP4qFM2NkEWIEhyt34emBB3AJ5Do3GDE9xQ2bcBq4KPY/pAwlSAUBVCEbyL+/DW4O3ei4NXAN4ET9PvRlGAXcAEyiOyP6d7CYhwyFT6XkGcHNSuANtVbhrhk26u6WPU4I+dGjh+0A5ciU0QgHCWoSQFcnDxXIQs2kmAW8GXExZxT76+lt1dVhH5khL0G2J3QvVbLVGRQHZqzKKwxwAzgSmqb54dBI8cPHOYi7zqMaXX1CqBN9y6gOg9fFDRy/MBhMTIwbIXarEBVCqCZ/ncC5yT9RjQaPX5gACuAU5wD1SpBrV3AAqT1V5aDFQ+NHj/YB3n382q5SMUKoLT+cYiZTbrf98OJH3wBGaeMoAHiB4chMhgL1VmBWizA24G3Jf0GApBDXMeL3L5sgPjBcvv5qqIiBVBa/yzE2xeXs6cWOoHPIgkbrtR5/GAcIouDoHIrEFgBNG/fOUguXtrZiQSkvgD0wsh1hSp1Hj84EpFJxV7CajKDFqg/lmKeReIRd2Inb6rC1xeorrz9RFp6e9XFpk78oId05x+AyOIc4G60BablCGQBlJfVhHjaZib9xGV4Ahn8/Qh/4ZcocR3HD2ajeEGDWoFKB4FHIIOONPMQ0mL3DuS8zL79/OWUoJ7iB+9ALHRgKlEAE1nCPaOCv4mb+xDh/8U54CN8kP7ddaBXp/GDA4F3UYFcy56omMtDgbcm/YQeWMBdSILIP52DZYRPkPPqMH6wFDjEwmDbleV76koGgUuBlyf9dC4UkNH6RSiC8RNqUMVwUAaHTvygG7EIs5J+eBdeYWEsndz8wrUdA+XHAb4WQGn9U5DWn7aR/wCSYDKiVVYq4CDUUfzAMLBO3jk4dUqTUeB3Fyz2PTloX7EIqc6RJnqQqN+ncUknj4I6ih/MM7AWNZt5jr33V74nBlGAJuBE0hXw2YUMyK7BdvBAtMJ3qJP4wTgwlvyn+9Cmjcv8Y0WeCqCY/xlIynZa2A58HEkhHwARfBzCd6iH+IFpFI+e2vr8jLZcn283EMQCHEV6HD9PI32va8p4nKQ/fmDNbjYGF44xB/j6f9/veVY5BcghS65bSZ4NSEv7qXMgKeE7+MQPLBIeMBtYY5rMoWOe6JrXdMlhl3meV24aOBkpcpA0f0bSpB5xDiQtfAeP+EEvkriSqBKYxtDrDhn/1CTTGNrpeY7bQc35M5tkWY+0/NQJ38ElfnAhEoEcSPjWZhct81DLMtl46eGuJ5TrAl6D1NZNgiLwfbT5dtqE76ApQSfiNXyhuquFg4E1ocUcmN9iDtCVn+B6jp8CNCPrzZIwY3kk22cVUoETSK/wHbT4wRAuNYRjxjQMa97uwf1y09rcvdd+Y4DxwCsTuOl+4EvIYs4u52Daha+SpsKQBtbhE1t2t4P1otv3fgowCYkuxUkn4t37Mkr8vZ6En0JmgrUfirdUxU8BZhNvjt9OxK17K9IFZIIPh3YkaPUfty9LxgDKDGA28bl/n0UydL5FJvywGYvPglE/C7A/8RRjfhJx7QZZwZNROU2ILF3xmgXkUAoxRchDwLvJhB810/GQtZcCtBJ9mnely7cyqmcKHptreClAMzAxopupaflWRlVMwmNZu9cYoJloijv5Lt9yW6ufEQrjsWsO6viNAcKeAfgu33LbMSQjNNrwGNB7WQCTcDNhepCy6NfjsoJHE75BamLqDUMzHi59Lwtg4mEyqsB3+dZpy9dz/6DFWMP3fjJqw9kttYSodw3bjqRW3Y7LCp5Tl693mrqxeQhjmolVzFp/rLgogAVQBKNQ6cU0nkb6+5IVPKcuX6/+Wlu/xUmP5a37Hoddx40xRpybEQp5PCKTJQpgARgUDaumipobEO/e750DbsJHypycj9S6+UsRdmWCj4Q8Hpa1VAHMIhbWkEmuz7CqWgrguXxLE/5EpBjyB4H/kZn+KOnHo85BiQIMmQWKRrHQXDB6jcpDAesRs1+ygkcT/nQk3n8GMj1JeuFEo9ONHWTTKVGAQm6QQi6fbxpqfqmCNllEcvFX47KCRxP+wUhe3bKk38ooYjceRbJLpgZD5hA9rZ17DIznA17cWb71UcoLf7597rKk38go4394LFAtUYB8boCDOxYUDMvcGuDC/YhzZ7X9I4Cn8BcCa4Hjkn4bo5AOgs4CDMukb0wXhmVsRwYOXgMB3+VbmvBPQMqsHEZG3BQQf4wrJRbgvJvPAsAyrM0onjsNtfpWP4zMz1OEbyKlZL9BJvyk6MPumt12JPV0vRoWm1BW5Sr4Lt9ShN+MxPtvxF6SlJEIXfgUw/ZxBRu7gOcYWRPIc/mWZvLbECVZg2yUnJEcm/FJUPELvnQj5dYcPJdvacJ3dru6lEz4aeBx3C054B8MyjO8Yue3iHevZAWPJvwpSFnWcwkvmphRPUNI4UjPaqflwq8PA99Gii6WE/5BSH//HjLhp4VO4FG/E1wVQBnU/Q3p85/Wv9OEPxf4OnAqWUw/TWzCTghxmwFA+fUAgyguRA/hH4lsDbuIjLTxIGUKW3oqgFdYVhP+sYgvoKLypBmxsAf4A2WqnVdkrjXhL0X8+pnwqyPqcdJmxAL4EnhJmCL8HHA6Es6NdnP7xqULuJbokm8NZAr/PHj3/xBQARThj0FmBJcgyQYZ1dGFxEYSJ7AFMKDdkhnBechSrowq8GuNSRBYASzZGPJ80lUxNKNGKlkWvhHYQUwVw/Vc9rS1nEahklnARmRPmshxK2Sw46r9Q9syPWOYSiyAU7btFGLaNaQIrdPy+SZjweE9CzslnrHwtjjKFkSLATRZFgXDiGwp9ANnPxLovEozgzYgtfJXRfZ2FKb19BZXzJ65+JiXOo8sGIaztj1texaUw7I/zpIsAzCN4ePOMznWuFjmmPNx/hbl2HrEfR+YQArwg3VLnKlgASnitIwYCkgfP+fgwT7DOMKQdQUNgwXkw2/9z6FkYQWlmsDNo4gSRJbI4Qz4+g0Do0FzBkJ+eRZwM2KhKyKwAvxg3RL1x24h4p0ylFF/vZn8JHgY2cvIguD9P1RoARQleBap5pmekpijl15EFlugMuFDbbH7dcgGCRnJcgdSc6kqKlYAxQr0Iileadw5a7TwBJKY0weVt36offXOY0hUsCfpNzEK6Ube/eO1XKQqBVCsAEhS6K1Jv41RhjMQv8M5UE3rhxosgKIEe5D6P79J+q2MIn6FvPMBqF74EN4Czq1I7b+nkn4zo4AnkHcdSnSsJgXQuoKHkIJQQdPKMyqnA9mPaG+Tr6X1QwgWQFOCnwAX45OJklE1ncBngJ85B2oVPoTUBWhewluBK8icRGHSA1wOfIcqvH1+hJbEoShBHskQugalbkBG1fQh072vYmdjhyV8CDmLR1GCAcRJdDWZJagFZ4f0GwhhxO9G6GlcihL0I/WBLyMbE1RDJ5JhfR0y1Q5d+BBRHp/mI/gispK4o9rrjUI6kHd2IxG1fIfIEjm1McFapCBkFjcoz5NI7sXeCixRCR8izuTVZgd3A2cCv47yN+sYC/HwnYlM9UId7XsReSq35id4GFiBbByRBZCG6UGqra0gRCdPEGLJ5deUYBuSYPIhhruE0bzq50mke1yN4t6NQ/gQYzEHTQn2IE6NU5Gl5r2MPiXoRZZxvRPZR2mP80VcwofoN4wYgaMESrLpo8iA5xhGz1SxCPwdmR3dhb2YA+IVvEMi5Vw0a9AN/AKZ9zY6mxC/yHLgeyQsfIjZAqi4WING5mngTmTrnMdRVoUnJXiHxBTAQbMGbvQFuU4KsZBB7g+RVVMbSZHgHRJXgADcjry4ZUg5mrSnp/cgY5u7EN/HM+qXaRG8Q6pH3gtve636v5ORSmRLkEHjLKQkbRroR/r3PyHjmQeQ7fL2kjbBO6RaAaBECWB4O/SjgKOBNyC7kOxLfIPaIvAS0rofBP4I/BXx4Y/YbS2tgndIvQI4uCgCiMAnAXOAI4B5wKuAA5ECTG3UbiX67U8XkoD5L6Rq6gbg30gdvpL8xbQL3qFuFEDFQxlAKpi1IzWMDkDK1860/30ZoiwTkBJtrQzXOupBHDF5ZDq6G9kTYQuSBrcF8WD2IIrgWnuvXoSuUpcKoOOjEOpztiKCd7ZRzTE8CC4gQi0iSpBHFMI3ibceBe72YhqWAIoRiEYQdEZGRkZGRkaGyv8BYcEZBeiG90YAAAAldEVYdGRhdGU6Y3JlYXRlADIwMTktMDEtMDlUMDA6NDY6MzkrMDg6MDC97/qTAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDE5LTAxLTA5VDAwOjQ2OjM5KzA4OjAwzLJCLwAAAEN0RVh0c29mdHdhcmUAL3Vzci9sb2NhbC9pbWFnZW1hZ2ljay9zaGFyZS9kb2MvSW1hZ2VNYWdpY2stNy8vaW5kZXguaHRtbL21eQoAAAAYdEVYdFRodW1iOjpEb2N1bWVudDo6UGFnZXMAMaf/uy8AAAAYdEVYdFRodW1iOjpJbWFnZTo6SGVpZ2h0ADI1NunDRBkAAAAXdEVYdFRodW1iOjpJbWFnZTo6V2lkdGgAMjU2ejIURAAAABl0RVh0VGh1bWI6Ok1pbWV0eXBlAGltYWdlL3BuZz+yVk4AAAAXdEVYdFRodW1iOjpNVGltZQAxNTQ2OTY1OTk55EYUXgAAABF0RVh0VGh1bWI6OlNpemUANjIxNkLRdRXqAAAAYHRFWHRUaHVtYjo6VVJJAGZpbGU6Ly8vaG9tZS93d3dyb290L25ld3NpdGUvd3d3LmVhc3lpY29uLm5ldC9jZG4taW1nLmVhc3lpY29uLmNuL2ZpbGVzLzUwLzUwNzY0OC5wbmfC6U99AAAAAElFTkSuQmCC");
        dfsFile.setExt("jpg");
        dfsFile.setFileName("feiji");

        //base64转byte[]
        String imgbase = dfsFile.getBase64();
        imgbase=imgbase.substring(imgbase.indexOf(",")+1, imgbase.length());

        byte[] decodeBase64 = Base64.decodeBase64(imgbase); //上传图片获取图片base64码,然后解码,然后转成字节数组,以流的形式输出到本地

        String[] file = FastDFSUtils.uploadFile(decodeBase64, dfsFile.getFileName(), dfsFile.getExt());

        return null;
    }

    @DeleteMapping("delete")
    public String delete(){
        String groupName = "group1";
        String fileId = "M00/00/00/J2mJ7F4JYgyATp5qAAASos_FTB0223.jpg";
        int i = FastDFSUtils.deleteFile(groupName,fileId);
        System.out.println(i);
        return null;
    }
}
