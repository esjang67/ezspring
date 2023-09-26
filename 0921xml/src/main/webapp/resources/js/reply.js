// 즉시 실행함수 (사용할 함수 등록시킴:모듈화)
let replyService = (function() {
  // 함수들을 선언함
  function add(reply, callback, error){
    console.log("댓글등록");

    // 요청 ajax
    $.ajax({
      type: 'post',
      url: '/replies/new',   // 다른 프로젝트일때는 원주소 포함해야함
      data: JSON.stringify(reply),   // reply를 객체형으로 받아올것임
      contentType: 'application/json; charset=utf-8',

      success: function(result, status, xhr){
        if(callback){
          callback(result);
        }
      },
      error: function(xhr, status, e) {
        if(error) {
          error(e);
        }
      }
    })
  }

  // 참고 : @GetMapping(value = "/pages/{bno}/{page}"
  function getList(param, callback, error){
    // param : 게시글번호, 페이지정보
    const bno = param.bno;
    const page = param.page || 1; // param.page가 없으면 1으로 
	
    // ajax로 해도됨
    $.getJSON('/replies/pages/' + bno + '/' + page + '.json',
	          function(data) {
	            if(callback){
	              callback(data);
	            }
	          })
  }
  
  function remove(rno, callback, error){
    $.ajax({
        type: 'delete',
        url: '/replies/' + rno,

        success: function(result, status, xhr){
          if(callback){
            callback(result);
          }
        },
        error: function(xhr, status, e){
          if(error){
            error(e);
          }
        }
    })
  }
  
  function modify(reply, callback, error){
    
    $.ajax({
        type: "put",
        url: "/replies/" + reply.rno,
        data: JSON.stringify(reply),
        contentType: 'application/json; charset=utf-8',

        success: function(result, status, xhr){
          if(callback){
            callback(result);
          }
        },
        error: function(xhr, status, e){
          if(error){
            error(e);
          }
        }
    })
  }

  function get(rno, callback, error){
    $.get('/replies/' + rno + '.json',
          function(result){
            if(callback){
              callback(result);
            }
          }    
    )
  }

  // 날짜 함수 (오늘날짜이면 시간표시)
  function displayTime(timeValue) {
    let today = new Date();

    let gap = today.getTime() - timeValue;
    let dateObj = new Date(timeValue);

    let str = '';
    
    // 24시간 계산
    if(gap < 12 * 60 * 60 * 1000){
      
      let h = dateObj.getHours();
      let m = dateObj.getMinutes();
      let s = dateObj.getSeconds();

      return h + ":" + m + ":" + s;

    } else {

      let y = dateObj.getFullYear();
      let m = dateObj.getMonth() + 1;
      let d = dateObj.getDate();

      return y + "/" + m + "/" + d;
    
    }
  }

  // 함수를 저장해둠
  return {
    add : add,     // k : v add라는 명령어로 add() 함수를 저장함
    getList : getList,
    remove : remove,
    modify : modify,
    get : get,
    displayTime : displayTime
  }

})();

