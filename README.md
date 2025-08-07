# 계양중학교 급식알리미

매일 오전 12시, 인스타그램 게시물과 스토리로 급식을 업로드합니다. [bs_meal_1nfo](https://github.com/Bruce0203/bs_meal_1nfo)를 포크했습니다.

## 기술 스택

- Kotlin
  - Actions 위에서 작동되는 프로그램
- GitHub Actions
  - cron을 이용해 매일 아침 자동으로 실행
  - GitHub Actions Runner가 필요합니다.
    - 인스타그램 API가 여러 디바이스에서 로그인하는 것을 허용하지 않기 때문입니다.
    - 현재 계정 운영자의 개인 서버에서 작동되고 있습니다.